package bluetooth.inuker.com.grassinvain.controller.fragment.yejifragnemt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.controller.adapter.PersonYejiAdapter;
import bluetooth.inuker.com.grassinvain.network.body.response.PageBody;
import bluetooth.inuker.com.grassinvain.network.body.response.PersonTeamShouyi;
import bluetooth.inuker.com.grassinvain.network.body.response.PersonTeamShouyiBody;
import bluetooth.inuker.com.grassinvain.network.model.UserModel;
import bluetooth.inuker.com.grassinvain.network.model.callback.Callback;

/**
 * Created by 1 on 2017/4/6.
 */

public class SalesResultperson extends Fragment {

    private View view;
    private SwipeRefreshLayout swipeRefreshPerson;
    private RecyclerView recyclerViewPerson;
    private List<PersonTeamShouyiBody> data = new ArrayList<>();
    private PersonYejiAdapter personYejiAdapter;
    private UserModel userModel;
    private RelativeLayout nodata;
    private LinearLayout sdata;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.salesresultperson_activity, null);
        userModel = new UserModel(getActivity());
        initData();
        initView();
        return view;
    }

    private void initData() {
        PageBody pageBody = new PageBody();
        pageBody.pageNum = 1;
        pageBody.pageSize = 10;
        userModel.getPersonTeamshouyi(pageBody, new Callback<PersonTeamShouyi>() {
            @Override
            public void onSuccess(PersonTeamShouyi personTeamShouyi) {
                if (null != personTeamShouyi.list) {
                    nodata.setVisibility(View.GONE);
                    sdata.setVisibility(View.VISIBLE);
                    List<PersonTeamShouyiBody> list = personTeamShouyi.list;
                    data.addAll(list);
                    personYejiAdapter.addAll(list);
                    personYejiAdapter.notifyDataSetHasChanged();
                }
                if (null == personTeamShouyi.list) {
                    nodata.setVisibility(View.VISIBLE);
                    sdata.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(int resultCode, String message) {

            }
        });
    }

    private void initView() {

        nodata = (RelativeLayout) view.findViewById(R.id.nodata);
        sdata = (LinearLayout) view.findViewById(R.id.data);
        swipeRefreshPerson = (SwipeRefreshLayout) view.findViewById(R.id.SwipeRefresh_person);
        //  设置加载是的属性
        swipeRefreshPerson.setColorSchemeResources(R.color.zhuticolor);
        swipeRefreshPerson.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                data.clear();
                personYejiAdapter.clear();
                initData();
                swipeRefreshPerson.setRefreshing(false);
            }
        });
        recyclerViewPerson = (RecyclerView) view.findViewById(R.id.recyclerView_person);
        recyclerViewPerson.setLayoutManager(new LinearLayoutManager(getActivity()));
        personYejiAdapter = new PersonYejiAdapter(getActivity(), data, R.layout.person_yeji_activity);
        recyclerViewPerson.setAdapter(personYejiAdapter);

    }
}
