package bluetooth.inuker.com.grassinvain.controller.fragment.aboutproduct;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.controller.adapter.AlloederFragmentAdapter;
import bluetooth.inuker.com.grassinvain.network.body.response.AllOrderBody;
import bluetooth.inuker.com.grassinvain.network.body.response.AllOrderfirstBody;
import bluetooth.inuker.com.grassinvain.network.body.response.PageBody;
import bluetooth.inuker.com.grassinvain.network.model.UserModel;
import bluetooth.inuker.com.grassinvain.network.model.callback.Callback;

/**
 * Created by 1 on 2017/4/19.
 */

public class AlloederFragment extends Fragment {

    private View view;
    private UserModel userModel;
    private SwipeRefreshLayout swipeRefreshAllOrder;
    private RecyclerView allorderrecycView;
    private List<AllOrderfirstBody> data = new ArrayList<>();
    private AlloederFragmentAdapter alloederFragmentAdapter;
    private int pagesize = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.all_order_fragment_activity, null);
        userModel = new UserModel(getActivity());
        initData(1);
        initView();
        return view;
    }

    private void initView() {

        // 原生刷新，控件
        swipeRefreshAllOrder = (SwipeRefreshLayout) view.findViewById(R.id.SwipeRefresh_all_order);
        //  设置加载是的属性
        swipeRefreshAllOrder.setColorSchemeResources(R.color.zhuticolor, R.color.color_red_pay);
        swipeRefreshAllOrder.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                data.clear();
                alloederFragmentAdapter.clear();
                initData(1);
                swipeRefreshAllOrder.setRefreshing(false);
                Toast.makeText(getActivity(), "刷新完毕", Toast.LENGTH_SHORT).show();
            }
        });
        allorderrecycView = (RecyclerView) view.findViewById(R.id.allorderrecycView);
        allorderrecycView.setLayoutManager(new LinearLayoutManager(getActivity()));
        alloederFragmentAdapter = new AlloederFragmentAdapter(getActivity(), data, R.layout.wait_pay_contont);
        allorderrecycView.setAdapter(alloederFragmentAdapter);

        /**
         * 实现下拉加载
         */
        allorderrecycView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //判断RecyclerView的状态 是空闲时，同时，是最后一个可见的ITEM时才加载
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == alloederFragmentAdapter.getItemCount()) {
                    Toast.makeText(getActivity(), "正在加载........", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            initData(pagesize++);
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
        /**
         * 判断按钮的点击事件
         */

        alloederFragmentAdapter.setOnItemClickListener(new AlloederFragmentAdapter.ChooseType() {
            @Override
            public void choosebutton1(int positon, String type, String order) {
                if ("去评价".equals(type)) {
                    Toast.makeText(getActivity(), "去评价", Toast.LENGTH_SHORT).show();
                    /*Intent intent1 = new Intent(getActivity(), EvaluationGoods.class);
                    intent1.putExtra("order", order);
                    startActivity(intent1);*/
                }
                if ("查看物流".equals(type)) {
                    Toast.makeText(getActivity(), "查看物流", Toast.LENGTH_SHORT).show();
                  /*  Intent intent1 = new Intent(getActivity(), WuliuActivity.class);
                    intent1.putExtra("order", order);
                    startActivity(intent1);*/
                }
                if ("取消订单".equals(type)) {
                    Toast.makeText(getActivity(), "取消订单", Toast.LENGTH_SHORT).show();
                    userModel.getCancelOrder(order, new Callback<Object>() {
                        @Override
                        public void onSuccess(Object o) {
                            Toast.makeText(getActivity(), "取消成功", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(int resultCode, String message) {

                        }
                    });
                }
            }

            @Override
            public void choosebutton2(int positon, String type, String order) {

                if ("去付款".equals(type)) {
                    Toast.makeText(getActivity(), "去付款", Toast.LENGTH_SHORT).show();
                }
                if ("确认收货".equals(type)) {
                    Toast.makeText(getActivity(), "确认收货", Toast.LENGTH_SHORT).show();
                    userModel.getQuerenOrder(order, new Callback<Object>() {
                        @Override
                        public void onSuccess(Object o) {
                            Toast.makeText(getActivity(), "已确认收货", Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onFailure(int resultCode, String message) {
                        }
                    });
                }
                if ("删除订单".equals(type)) {
                    Toast.makeText(getActivity(), "删除订单", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void initData(int pagesize) {
        PageBody pageBody = new PageBody();
        pageBody.pageNum = pagesize;
        pageBody.pageSize = 10;
        pageBody.status = "";
        userModel.getAllOrder(pageBody, new Callback<AllOrderBody>() {
            @Override
            public void onSuccess(AllOrderBody allOrderBody) {
                List<AllOrderfirstBody> list = allOrderBody.list;
                if (0 == list.size()) {
                    Toast.makeText(getActivity(), "数据已全部加载完毕", Toast.LENGTH_SHORT).show();
                }
                data.addAll(list);
                alloederFragmentAdapter.addAll(list);
                alloederFragmentAdapter.notifyDataSetHasChanged();
            }

            @Override
            public void onFailure(int resultCode, String message) {

            }
        });
    }


}
