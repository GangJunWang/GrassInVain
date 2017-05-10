package bluetooth.inuker.com.grassinvain.controller.fragment.aboutproduct;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.controller.adapter.dingdanadapter.WaitPayFragmentAdapter;
import bluetooth.inuker.com.grassinvain.network.body.response.AllOrderBody;
import bluetooth.inuker.com.grassinvain.network.body.response.AllOrderfirstBody;
import bluetooth.inuker.com.grassinvain.network.body.response.PageBody;
import bluetooth.inuker.com.grassinvain.network.model.UserModel;
import bluetooth.inuker.com.grassinvain.network.model.callback.Callback;

/**
 * Created by 1 on 2017/4/7.
 */

public class WaitPayFragment extends Fragment implements View.OnClickListener {

    private View view;
    private RecyclerView waitPayRecycView;
    private SwipeRefreshLayout swipeRefreshWiatPay;
    private List<AllOrderfirstBody> data = new ArrayList<>();
    private UserModel userModel;
    private WaitPayFragmentAdapter waitPayFragmentAdapter;
    private Dialog mCameraDialog;
    private ImageView zhifubao_src;
    private ImageView weixin_src;
    private String order1;
    private int pagersize = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.wait_pay_fragment_activity, null);
        userModel = new UserModel(getActivity());
        initData(1);
        initView();
        return view;

    }

    private void initView() {
        // 原生刷新，控件
        swipeRefreshWiatPay = (SwipeRefreshLayout) view.findViewById(R.id.SwipeRefresh_wiat_pay);
        //  设置加载是的属性
        swipeRefreshWiatPay.setColorSchemeResources(R.color.zhuticolor, R.color.color_red_pay);
        swipeRefreshWiatPay.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                data.clear();
                waitPayFragmentAdapter.clear();
                initData(1);
                swipeRefreshWiatPay.setRefreshing(false);
                Toast.makeText(getActivity(), "刷新完毕", Toast.LENGTH_SHORT).show();
            }
        });
        // 复用布局
        waitPayRecycView = (RecyclerView) view.findViewById(R.id.wait_pay_recycView);
        //设置布局
        waitPayRecycView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //适配器
        waitPayFragmentAdapter = new WaitPayFragmentAdapter(getActivity(), data, R.layout.wait_pay_contont);
        waitPayRecycView.setAdapter(waitPayFragmentAdapter);
        waitPayFragmentAdapter.setOnItemClickListener(new WaitPayFragmentAdapter.ChooseType() {

            @Override
            public void chooseCancle(int positon, String type, String order) {
                if ("取消订单".equals(type)) {
                    userModel.getCancelOrder(order, new Callback<Object>() {
                        @Override
                        public void onSuccess(Object o) {
                            Toast.makeText(getActivity(), "取消成功", Toast.LENGTH_SHORT).show();
                            data.clear();
                            waitPayFragmentAdapter.clear();
                            initData(1);
                        }

                        @Override
                        public void onFailure(int resultCode, String message) {
                        }
                    });
                }
            }

            @Override
            public void choosePay(int positon, String type, String order) {
                order1 = order;
                if ("去付款".equals(type)) {
                    popupWindow();
                }
            }
        });

        /**
         * 实现上拉加载
         */
        waitPayRecycView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == waitPayFragmentAdapter.getItemCount()) {
                    Toast.makeText(getActivity(), "正在加载........", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            initData(pagersize++);
                        }
                    }, 500);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //最后一个可见的ITEM
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }
        });

    }

    private void initData(int pagersize) {
        PageBody pageBody = new PageBody();
        pageBody.pageNum = pagersize;
        pageBody.pageSize = 10;
        pageBody.status = "PAY";
        userModel.getAllOrder(pageBody, new Callback<AllOrderBody>() {
            @Override
            public void onSuccess(AllOrderBody allOrderBody) {
                List<AllOrderfirstBody> list = allOrderBody.list;
                if (0 == list.size()) {
                    Toast.makeText(getActivity(), "已全部加载完毕", Toast.LENGTH_SHORT).show();
                }
                data.addAll(list);
                waitPayFragmentAdapter.addAll(list);
                waitPayFragmentAdapter.notifyDataSetHasChanged();
            }

            @Override
            public void onFailure(int resultCode, String message) {
            }
        });
    }
    private void popupWindow() {
        mCameraDialog = new Dialog(getActivity(), R.style.my_dialog);
        LinearLayout root = (LinearLayout) LayoutInflater.from(getActivity()).inflate(
                R.layout.layout_paytype_control, null);
        root.findViewById(R.id.zhifubao).setOnClickListener(this);
        root.findViewById(R.id.weixin).setOnClickListener(this);
        root.findViewById(R.id.queren).setOnClickListener(this);
        // 切换图片
        zhifubao_src = (ImageView) root.findViewById(R.id.zhifubao_src);
        weixin_src = (ImageView) root.findViewById(R.id.weixin_src);
        mCameraDialog.setContentView(root);
        Window dialogWindow = mCameraDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.dialogstyle); // 添加动画
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        // lp.x = 0; // 新位置X坐标
        // lp.y = -20; // 新位置Y坐标
        // lp.width = (int) getResources().getDisplayMetrics().widthPixels; // 宽度
        //lp.height = 300; // 高度
        // lp.alpha = 9f; // 透明度
        // root.measure(0, 0);
        lp.height = 600;
        // lp.alpha = 9f; // 透明度
        dialogWindow.setAttributes(lp);
        mCameraDialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.zhifubao:
                zhifubao_src.setImageResource(R.mipmap.xuanzhong);
                weixin_src.setImageResource(R.mipmap.weixuan);
                break;
            case R.id.weixin:
                zhifubao_src.setImageResource(R.mipmap.weixuan);
                weixin_src.setImageResource(R.mipmap.xuanzhong);
                break;
            case R.id.queren:
                userModel.getSubmitOrdernumber(order1, new Callback<Object>() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(getActivity(), "支付成功", Toast.LENGTH_SHORT).show();
                        mCameraDialog.cancel();
                    }
                    @Override
                    public void onFailure(int resultCode, String message) {
                    }
                });
                break;
        }
    }
}
