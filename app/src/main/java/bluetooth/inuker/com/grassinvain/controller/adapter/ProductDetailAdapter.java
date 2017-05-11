package bluetooth.inuker.com.grassinvain.controller.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.shaojun.widget.superAdapter.SuperAdapter;
import com.shaojun.widget.superAdapter.internal.SuperViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.network.body.response.ProductPersonBody;

/**
 * Created by 1 on 2017/3/31.
 */

public class ProductDetailAdapter extends SuperAdapter<ProductPersonBody> {

    /**
     * Constructor for single itemView type.
     *
     * @param context
     * @param items
     * @param layoutResId
     */
    public ProductDetailAdapter(Context context, List<ProductPersonBody> items, int layoutResId) {
        super(context, items, layoutResId);
    }


    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, ProductPersonBody item) {
        //头像
        ImageView imageView = holder.findViewById(R.id.speak_image);
        Picasso.with(mContext).load(item.avatarUrl).placeholder(R.mipmap.chanpintu).into(imageView);
        //姓名
        TextView name = holder.findViewById(R.id.name);
        name.setText(item.userName);
        //时间
        TextView time = holder.findViewById(R.id.speak_time);
        time.setText(item.createAt);
        //评论内容
        TextView speakCOntont = holder.findViewById(R.id.speak_contont);
        speakCOntont.setText(item.evaluateContent);

    }
}
