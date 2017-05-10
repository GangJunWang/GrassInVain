package bluetooth.inuker.com.grassinvain.controller.fragment.yejifragnemt;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shaojun.widget.superAdapter.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.controller.activity.TeamYejiDetail;
import bluetooth.inuker.com.grassinvain.controller.adapter.PersonYejiAdapter;
import bluetooth.inuker.com.grassinvain.network.body.response.PersonYejiBody;

/**
 * Created by 1 on 2017/4/6.
 */

public class SalesResultSalesteam extends Fragment {

    private View view;
    private SwipeRefreshLayout swipeRefreshTeam;
    private RecyclerView recyclerViewTeam;
    private List<PersonYejiBody> data = new ArrayList<>();
    private PersonYejiAdapter personYejiAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.salesresultsalesteam_activity, null);
        initData();
        initView();
        return view;
    }

    private void initData() {
        data.add(new PersonYejiBody());
        data.add(new PersonYejiBody());
        data.add(new PersonYejiBody());
        data.add(new PersonYejiBody());
        data.add(new PersonYejiBody());
        data.add(new PersonYejiBody());
        data.add(new PersonYejiBody());
    }

    private void initView() {

        swipeRefreshTeam = (SwipeRefreshLayout) view.findViewById(R.id.SwipeRefresh_team);
        swipeRefreshTeam.setColorSchemeResources(R.color.zhuticolor);
        swipeRefreshTeam.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                data.add(new PersonYejiBody());
                data.add(new PersonYejiBody());
                data.add(new PersonYejiBody());
                data.add(new PersonYejiBody());
                data.add(new PersonYejiBody());
                personYejiAdapter.addAll(data);
                personYejiAdapter.notifyDataSetHasChanged();
                swipeRefreshTeam.setRefreshing(false);
            }
        });
        recyclerViewTeam = (RecyclerView) view.findViewById(R.id.recyclerView_team);
        recyclerViewTeam.setLayoutManager(new LinearLayoutManager(getActivity()));
        personYejiAdapter = new PersonYejiAdapter(getActivity(),data, R.layout.team_yeji_activity);
        recyclerViewTeam.setAdapter(personYejiAdapter);
        personYejiAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int viewType, int position) {
                Intent intent = new Intent(getActivity(), TeamYejiDetail.class);
                startActivity(intent);
            }
        });


    }
}
