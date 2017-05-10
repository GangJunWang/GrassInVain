package bluetooth.inuker.com.grassinvain.controller.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shaojun.widget.superAdapter.SuperAdapter;
import com.shaojun.widget.superAdapter.internal.SuperViewHolder;

import java.util.List;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.common.cache.ImageLoaderKit;
import bluetooth.inuker.com.grassinvain.common.model.ImageModel;

/**
 * Created by 1 on 2017/4/11.
 */

public class FeedBackAdapter extends SuperAdapter<ImageModel> {
    /**
     * Constructor for single itemView type.
     *
     * @param context
     * @param items
     * @param layoutResId
     */
    public FeedBackAdapter(Context context, List<ImageModel> items, int layoutResId) {
        super(context, items, layoutResId);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, ImageModel item) {


        ImageView iv_feed_back_content = holder.findViewById(R.id.iv_feed_back_content);
        TextView textView = holder.findViewById(R.id.textView10);
        if (TextUtils.isEmpty(item.path)) {
            iv_feed_back_content.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(), item.resId));
        } else {
            ImageLoaderKit.getInstance().displayImage(item.path, iv_feed_back_content);
            textView.setVisibility(View.GONE);
        }
    }

}
