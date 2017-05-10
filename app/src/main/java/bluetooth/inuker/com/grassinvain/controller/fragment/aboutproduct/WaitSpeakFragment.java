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
import bluetooth.inuker.com.grassinvain.controller.activity.EvaluationGoods;
import bluetooth.inuker.com.grassinvain.controller.adapter.dingdanadapter.WaitSpeakFragmentAdapter;
import bluetooth.inuker.com.grassinvain.network.body.response.AllOrderBody;
import bluetooth.inuker.com.grassinvain.network.body.response.AllOrderfirstBody;
import bluetooth.inuker.com.grassinvain.network.body.response.PageBody;
import bluetooth.inuker.com.grassinvain.network.model.UserModel;
import bluetooth.inuker.com.grassinvain.network.model.callback.Callback;

/**
 * Created by 1 on 2017/4/7.
 */

public class WaitSpeakFragment extends Fragment {

    private View view;
    private List<AllOrderfirstBody> data = new ArrayList<>();
    private SwipeRefreshLayout swipeRefresh_wiat_speak;
    private RecyclerView wait_speak_recycView;
    private UserModel userModel;
    private WaitSpeakFragmentAdapter waitSpeakFragmentAdapter;
    private int pagersize = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.wait_speak_fragment_activity, null);
        userModel = new UserModel(getActivity());
        initData(1);
        initView();
        return view;
    }

    private void initView() {

        // 刷新控件
        swipeRefresh_wiat_speak = (SwipeRefreshLayout) view.findViewById(R.id.SwipeRefresh_wiat_speak);
        // 设置刷新的样式
        swipeRefresh_wiat_speak.setColorSchemeResources(R.color.zhuticolor, R.color.color_red_pay);
        // 刷新监听
        swipeRefresh_wiat_speak.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                data.clear();
                waitSpeakFragmentAdapter.clear();
                initData(1);
                swipeRefresh_wiat_speak.setRefreshing(false);
            }
        });
        // 复用控件
        wait_speak_recycView = (RecyclerView) view.findViewById(R.id.wait_speak_recycView);
        wait_speak_recycView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // 适配器准备
        waitSpeakFragmentAdapter = new WaitSpeakFragmentAdapter(getActivity(), data, R.layout.wait_pay_contont);
        wait_speak_recycView.setAdapter(waitSpeakFragmentAdapter);
        waitSpeakFragmentAdapter.setOnItemClickListener(new WaitSpeakFragmentAdapter.ChooseType() {
            @Override
            public void chooseSpeak(int positon, String type, String order) {
                if ("去评价".equals(type)){
                    Intent intent1 = new Intent(getActivity(), EvaluationGoods.class);
                    intent1.putExtra("order",order);
                    startActivity(intent1);
                }
            }

            @Override
            public void choosedetele(int positon, String type, String order) {
                if ("删除订单".equals(type)){
                    Toast.makeText(getActivity(),"暂不支持删除",Toast.LENGTH_SHORT).show();
                }
            }
        });

        /**
         * 实现上拉加载
         */
        wait_speak_recycView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == waitSpeakFragmentAdapter.getItemCount()) {
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
    private void initData(int pagesize) {
        PageBody pageBody = new PageBody();
        pageBody.pageNum = pagesize;
        pageBody.pageSize = 10;
        pageBody.status = "DPJ";
        userModel.getAllOrder(pageBody, new Callback<AllOrderBody>() {
            @Override
            public void onSuccess(AllOrderBody allOrderBody) {
                List<AllOrderfirstBody> list = allOrderBody.list;
                if (0 == list.size()){
                    Toast.makeText(getActivity(),"数据已全部加载完毕",Toast.LENGTH_SHORT).show();
                }
                data.addAll(list);
                waitSpeakFragmentAdapter.addAll(list);
            }
            @Override
            public void onFailure(int resultCode, String message) {
            }
        });
    }
}
