package bluetooth.inuker.com.grassinvain.controller.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.controller.adapter.HomeAdapter;
import bluetooth.inuker.com.grassinvain.controller.adapter.MessageAdapter;
import bluetooth.inuker.com.grassinvain.network.body.response.NewMessageBody;
import bluetooth.inuker.com.grassinvain.network.body.response.NewMessageChiredBody;
import bluetooth.inuker.com.grassinvain.network.body.response.PageBody;
import bluetooth.inuker.com.grassinvain.network.model.UserModel;
import bluetooth.inuker.com.grassinvain.network.model.callback.Callback;

/**
 * Created by Super-me on 2017/3/25.
 */

public class MessageFragment extends Fragment {

    private View view;
    private RecyclerView homeRecyclerview;
    private List<NewMessageChiredBody> data = new ArrayList<>();
    private HomeAdapter homeAdapter;
    private UserModel userModel;
    private MessageAdapter messageAdapter;
    private SwipeRefreshLayout message_refreshlayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_message, null);
        userModel = new UserModel(getActivity());
        initData();
        initView();
        return view;
    }
    @Override
    public void onResume() {
        initView();
        super.onResume();
    }

    private void initData() {
        PageBody pageBody = new PageBody();
        pageBody.pageNum = 10;
        pageBody.pageSize = 1;
        userModel.getNewMessageList(pageBody, new Callback<NewMessageBody>() {
            @Override
            public void onSuccess(NewMessageBody newMessageBody) {

                List<NewMessageChiredBody> list = newMessageBody.list;
                data .clear();
                data.addAll(list);
                messageAdapter.clear();
                messageAdapter.addAll(list);
                messageAdapter.notifyDataSetHasChanged();
            }
            @Override
            public void onFailure(int resultCode, String message) {
            }
        });

    }
    private void initView() {
        // 刷新列表
        message_refreshlayout = (SwipeRefreshLayout) view.findViewById(R.id.message_refreshlayout);
        //设置刷新颜色
        message_refreshlayout.setColorSchemeResources(R.color.zhuticolor,R.color.color_red_pay);
        // 设置刷新监听
        message_refreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                data.clear();
                initData();
                message_refreshlayout.setRefreshing(false);
                Toast.makeText(getActivity(), "刷新完毕", Toast.LENGTH_SHORT).show();
            }
        });

        homeRecyclerview = (RecyclerView) view.findViewById(R.id.home_recyclerview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1) {
            @Override
            public boolean canScrollVertically() {
                return true;
            }
        };
        homeRecyclerview.setLayoutManager(gridLayoutManager);
        messageAdapter = new MessageAdapter(getActivity(),data, R.layout.new_message_contont);
        homeRecyclerview.setAdapter(messageAdapter);
    }




}
