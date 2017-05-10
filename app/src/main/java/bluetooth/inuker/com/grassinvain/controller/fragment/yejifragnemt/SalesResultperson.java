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

import java.util.ArrayList;
import java.util.List;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.controller.adapter.PersonYejiAdapter;
import bluetooth.inuker.com.grassinvain.network.body.response.PersonYejiBody;

/**
 * Created by 1 on 2017/4/6.
 */

public class SalesResultperson extends Fragment {

    private View view;
    private SwipeRefreshLayout swipeRefreshPerson;
    private RecyclerView recyclerViewPerson;
    private List<PersonYejiBody> data = new ArrayList<>();
    private PersonYejiAdapter personYejiAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.salesresultperson_activity, null);
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

        swipeRefreshPerson = (SwipeRefreshLayout) view.findViewById(R.id.SwipeRefresh_person);
        //  设置加载是的属性
        swipeRefreshPerson.setColorSchemeResources(R.color.zhuticolor);
        swipeRefreshPerson.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                data.add(new PersonYejiBody());
                data.add(new PersonYejiBody());
                data.add(new PersonYejiBody());
                data.add(new PersonYejiBody());
                data.add(new PersonYejiBody());
                personYejiAdapter.addAll(data);
                personYejiAdapter.notifyDataSetHasChanged();
                swipeRefreshPerson.setRefreshing(false);
            }
        });
        recyclerViewPerson = (RecyclerView) view.findViewById(R.id.recyclerView_person);
        recyclerViewPerson.setLayoutManager(new LinearLayoutManager(getActivity()));
        personYejiAdapter = new PersonYejiAdapter(getActivity(),data, R.layout.person_yeji_activity);
        recyclerViewPerson.setAdapter(personYejiAdapter);

    }
}
