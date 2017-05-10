package bluetooth.inuker.com.grassinvain.controller.adapter;

import android.content.Context;

import com.shaojun.widget.superAdapter.SuperAdapter;
import com.shaojun.widget.superAdapter.internal.SuperViewHolder;

import java.util.List;

import bluetooth.inuker.com.grassinvain.network.body.response.PersonYejiBody;


/**
 * Created by 1 on 2017/4/7.
 */

public class MyTeamDetailConyontAdapter extends SuperAdapter<PersonYejiBody> {

    /**
     * Constructor for single itemView type.
     *
     * @param context
     * @param items
     * @param layoutResId
     */
    public MyTeamDetailConyontAdapter(Context context, List<PersonYejiBody> items, int layoutResId) {
        super(context, items, layoutResId);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, PersonYejiBody item) {

    }
}
