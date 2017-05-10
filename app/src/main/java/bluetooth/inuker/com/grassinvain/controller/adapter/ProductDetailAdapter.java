package bluetooth.inuker.com.grassinvain.controller.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.shaojun.widget.superAdapter.SuperAdapter;
import com.shaojun.widget.superAdapter.internal.SuperViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.network.body.response.ProductSpeakBody;

/**
 * Created by 1 on 2017/3/31.
 */

public class ProductDetailAdapter extends SuperAdapter<ProductSpeakBody> {

    /**
     * Constructor for single itemView type.
     *
     * @param context
     * @param items
     * @param layoutResId
     */
    public ProductDetailAdapter(Context context, List<ProductSpeakBody> items, int layoutResId) {
        super(context, items, layoutResId);
    }


    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, ProductSpeakBody item) {
        //头像
       ImageView imageView = holder.findViewById(R.id.speak_image);
        Picasso.with(mContext).load(item.imageUrl).placeholder(R.mipmap.chanpintu).into(imageView);
        // 举报
        ImageView jubao = holder.findViewById(R.id.speak_jubao);
        //时间
        TextView time = holder.findViewById(R.id.speak_time);
        //规格
        TextView huige = holder.findViewById(R.id.speak_guige);
        //规格内容
        TextView guigeConton = holder.findViewById(R.id.speak_guige_contont);
        //评论内容
        TextView speakCOntont = holder.findViewById(R.id.speak_contont);

    }
}
