package bluetooth.inuker.com.grassinvain.controller.adapter;

import android.content.Context;
import android.widget.TextView;

import com.shaojun.widget.superAdapter.SuperAdapter;
import com.shaojun.widget.superAdapter.internal.SuperViewHolder;

import java.util.List;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.network.body.response.PersonShouyiZDChiredBody;

/**
 * Created by 1 on 2017/4/10.
 */

public class ZhangDanDetailAdapter extends SuperAdapter<PersonShouyiZDChiredBody> {

    /**
     * Constructor for single itemView type.
     *
     * @param context
     * @param items
     * @param layoutResId
     */
    public ZhangDanDetailAdapter(Context context, List<PersonShouyiZDChiredBody> items, int layoutResId) {
        super(context, items, layoutResId);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, PersonShouyiZDChiredBody item) {

        TextView month = holder.findViewById(R.id.textView8);
        TextView month_monery = holder.findViewById(R.id.month_monery);
        if (null != item.months){
            month.setText(item.months+ "月");
        }
        if (null != item.inComeMoney){
            month_monery.setText("+" +item.inComeMoney);
        }
    }
}
