package bluetooth.inuker.com.grassinvain.controller.adapter;

import android.content.Context;
import android.widget.TextView;

import com.shaojun.widget.superAdapter.SuperAdapter;
import com.shaojun.widget.superAdapter.internal.SuperViewHolder;

import java.util.List;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.network.body.response.NewMessageChiredBody;

/**
 * Created by 1 on 2017/4/4.
 */

public class MessageAdapter extends SuperAdapter<NewMessageChiredBody>{

    /**
     * Constructor for single itemView type.
     *
     * @param context
     * @param items
     * @param layoutResId
     */
    public MessageAdapter(Context context, List<NewMessageChiredBody> items, int layoutResId) {
        super(context, items, layoutResId);
    }
    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, NewMessageChiredBody item) {
        TextView new_message_title = holder.findViewById(R.id.new_message_titile);
        TextView new_message_time = holder.findViewById(R.id.new_message_time);
        TextView new_message_contont = holder.findViewById(R.id.new_message_contont);
        /**
         * 设置数据
         */
        if (null != item.title){
            new_message_title.setText(item.title);
        }
        if (null != item.modifyAt){
            new_message_time.setText(item.modifyAt);
        }
        if (null != item.content){
            new_message_contont.setText(item.content);
        }
    }
}
