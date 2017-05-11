package bluetooth.inuker.com.grassinvain.controller.adapter;

import android.content.Context;

import com.shaojun.widget.superAdapter.SuperAdapter;
import com.shaojun.widget.superAdapter.internal.SuperViewHolder;

import java.util.List;

/**
 * Created by 1 on 2017/5/11.
 */

public class WuliuRecycAdapter extends SuperAdapter<Object> {
    /**
     * Constructor for single itemView type.
     *
     * @param context
     * @param items
     * @param layoutResId
     */
    public WuliuRecycAdapter(Context context, List<Object> items, int layoutResId) {
        super(context, items, layoutResId);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, Object item) {

    }
}
