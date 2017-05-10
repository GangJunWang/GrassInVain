package bluetooth.inuker.com.grassinvain.controller.fragment.aboutproduct;

import android.content.Intent;
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
import bluetooth.inuker.com.grassinvain.controller.activity.personactivity.WuliuActivity;
import bluetooth.inuker.com.grassinvain.controller.adapter.dingdanadapter.AlreadlyPayAdapter;
import bluetooth.inuker.com.grassinvain.network.body.response.AllOrderBody;
import bluetooth.inuker.com.grassinvain.network.body.response.AllOrderfirstBody;
import bluetooth.inuker.com.grassinvain.network.body.response.PageBody;
import bluetooth.inuker.com.grassinvain.network.model.UserModel;
import bluetooth.inuker.com.grassinvain.network.model.callback.Callback;

/**
 * Created by 1 on 2017/4/7.
 */

public class AlreadyPayFragment extends Fragment {

    private List<AllOrderfirstBody> data = new ArrayList<>();
    private SwipeRefreshLayout swipeRefresh_alreadly_pay;
    private View view;
    private RecyclerView alreadly_pay_recycView;
    private UserModel userModel;
    private AlreadlyPayAdapter alreadlyPayAdapter;
    private int pagersize = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.already_pay_fragment_activity, null);
        userModel = new UserModel(getActivity());
        initData(1);
        initView();
        return view;
    }

    private void initView() {

        // 外部刷新控件
        swipeRefresh_alreadly_pay = (SwipeRefreshLayout) view.findViewById(R.id.SwipeRefresh_alreadly_pay);
        // 设置刷新控件的变化颜色
        swipeRefresh_alreadly_pay.setColorSchemeResources(R.color.zhuticolor, R.color.color_red_pay);
        //刷新监听
        swipeRefresh_alreadly_pay.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                data.clear();
                alreadlyPayAdapter.clear();
                initData(1);
                swipeRefresh_alreadly_pay.setRefreshing(false);
            }
        });
        // 复用控件
        alreadly_pay_recycView = (RecyclerView) view.findViewById(R.id.alreadly_pay_recycView);
        alreadly_pay_recycView.setLayoutManager(new LinearLayoutManager(getActivity()));
        alreadlyPayAdapter = new AlreadlyPayAdapter(getActivity(), data, R.layout.wait_pay_contont);
        alreadly_pay_recycView.setAdapter(alreadlyPayAdapter);
        //适配器准备
        alreadlyPayAdapter.setOnItemClickListener(new AlreadlyPayAdapter.ChooseType() {
            @Override
            public void chooseWuliu(int positon, String type, String order) {

                Intent intent1 = new Intent(getActivity(), WuliuActivity.class);
                intent1.putExtra("order",order);
                startActivity(intent1);
            }
            @Override
            public void chooseShouhuo(int positon, String type, String order) {
                userModel.getQuerenOrder(order, new Callback<Object>() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(getActivity(),"已确认收货",Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(int resultCode, String message) {

                    }
                });
            }
        });

        /**
         * 实现上拉加载
         */
        alreadly_pay_recycView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == alreadlyPayAdapter.getItemCount()) {
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
        pageBody.status = "PAD";
        userModel.getAllOrder(pageBody, new Callback<AllOrderBody>() {
            @Override
            public void onSuccess(AllOrderBody allOrderBody) {
                List<AllOrderfirstBody> list = allOrderBody.list;
                if (0 == list.size()){
                    Toast.makeText(getActivity(),"数据已全部加载完毕",Toast.LENGTH_SHORT).show();
                }
                data.addAll(list);
                alreadlyPayAdapter.addAll(list);
                alreadlyPayAdapter.notifyDataSetHasChanged();
            }
            @Override
            public void onFailure(int resultCode, String message) {

            }
        });
    }
}
