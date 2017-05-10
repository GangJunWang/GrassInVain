package bluetooth.inuker.com.grassinvain.controller.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.shaojun.widget.superAdapter.SuperAdapter;
import com.shaojun.widget.superAdapter.internal.SuperViewHolder;

import java.util.ArrayList;
import java.util.List;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.network.body.response.PersonYejiBody;

/**
 * Created by 1 on 2017/4/7.
 */

public class TeamYejiAdapter extends SuperAdapter<PersonYejiBody> {

    private List<PersonYejiBody> data = new ArrayList<>();
    /**
     * Constructor for single itemView type.
     *
     * @param context
     * @param items
     * @param layoutResId
     */
    public TeamYejiAdapter(Context context, List<PersonYejiBody> items, int layoutResId) {
        super(context, items, layoutResId);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, PersonYejiBody item) {

        data.add(new PersonYejiBody());
        data.add(new PersonYejiBody());
        data.add(new PersonYejiBody());
        data.add(new PersonYejiBody());
        data.add(new PersonYejiBody());

        //  头布局
        TextView teamContontText1 = holder.findViewById(R.id.team_contont_text1);
        TextView teamContontText2 = holder.findViewById(R.id.team_contont_text2);
        // 子RecyclerView
        RecyclerView teamRecyclerviewContong = holder.findViewById(R.id.team_recyclerview_contong);
        teamRecyclerviewContong.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        //  子RecyclerView的适配器 应该放在外边  而且应该设置高度 不然有可能不会显示
        MyTeamDetailConyontAdapter myTeamDetailConyontAdapter = new MyTeamDetailConyontAdapter(getContext(), data, R.layout.team_detail_activity_contont);
        teamRecyclerviewContong.setAdapter(myTeamDetailConyontAdapter);

    }
}
