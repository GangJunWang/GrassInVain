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

public class SalesResultSalesteam extends Fragment {

    private View view;
    private SwipeRefreshLayout swipeRefreshTeam;
    private RecyclerView recyclerViewTeam;
    private List<PersonTeamShouyiBody> data = new ArrayList<>();
    private PersonYejiAdapter personYejiAdapter;
    private RelativeLayout nodata;
    private LinearLayout sdata;
    private UserModel userModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.salesresultsalesteam_activity, null);
        userModel = new UserModel(getActivity());
        initData();
        initView();
        return view;
    }

    private void initData() {
        PageBody pageBody = new PageBody();
        pageBody.pageNum = 1;
        pageBody.pageSize = 10;
        userModel.getPersonTeamshouyi2(pageBody, new Callback<PersonTeamShouyi>() {
            @Override
            public void onSuccess(PersonTeamShouyi personTeamShouyi) {
                if (null == personTeamShouyi.list) {
                    nodata.setVisibility(View.VISIBLE);
                    sdata.setVisibility(View.GONE);
                }
                if (null != personTeamShouyi.list) {
                    nodata.setVisibility(View.GONE);
                    sdata.setVisibility(View.VISIBLE);
                    data.addAll(personTeamShouyi.list);
                    personYejiAdapter.addAll(personTeamShouyi.list);
                    personYejiAdapter.notifyDataSetHasChanged();
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
        swipeRefreshTeam = (SwipeRefreshLayout) view.findViewById(R.id.SwipeRefresh_team);
        swipeRefreshTeam.setColorSchemeResources(R.color.zhuticolor);
        swipeRefreshTeam.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                data.clear();
                personYejiAdapter.clear();
                initData();
                swipeRefreshTeam.setRefreshing(false);
            }
        });
        recyclerViewTeam = (RecyclerView) view.findViewById(R.id.recyclerView_team);
        recyclerViewTeam.setLayoutManager(new LinearLayoutManager(getActivity()));
        personYejiAdapter = new PersonYejiAdapter(getActivity(), data, R.layout.team_yeji_activity);
        recyclerViewTeam.setAdapter(personYejiAdapter);
/*        personYejiAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int viewType, int position) {
                Intent intent = new Intent(getActivity(), TeamYejiDetail.class);
                startActivity(intent);
            }
        });*/


    }
}
