package bluetooth.inuker.com.grassinvain.controller.adapter;

import android.content.Context;
import android.widget.TextView;

import com.shaojun.widget.superAdapter.SuperAdapter;
import com.shaojun.widget.superAdapter.internal.SuperViewHolder;

import java.util.ArrayList;
import java.util.List;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.network.body.response.PersonTeamShouyiBody;


/**
 * Created by 1 on 2017/4/6.
 */

public class PersonYejiAdapter extends SuperAdapter<PersonTeamShouyiBody> {

    private List<Object> data = new ArrayList<>();

    /**
     * Constructor for single itemView type.
     *
     * @param context
     * @param items
     * @param layoutResId
     */
    public PersonYejiAdapter(Context context, List<PersonTeamShouyiBody> items, int layoutResId) {
        super(context, items, layoutResId);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, PersonTeamShouyiBody item) {
        TextView month = holder.findViewById(R.id.month);
        month.setText(item.months + "月销售总额");
        TextView monery = holder.findViewById(R.id.monery);
        monery.setText("+" + item.inComeMoney);
    }
}
