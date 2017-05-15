package bluetooth.inuker.com.grassinvain.controller.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.shaojun.widget.superAdapter.SuperAdapter;
import com.shaojun.widget.superAdapter.internal.SuperViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.network.body.response.ProductSDeatilBody;

/**
 * Created by 1 on 2017/4/18.
 */

public class OrderListAdapter extends SuperAdapter<ProductSDeatilBody> {

    /**
     * Constructor for single itemView type.
     *
     * @param context
     * @param items
     * @param layoutResId
     */
    public OrderListAdapter(Context context, List<ProductSDeatilBody> items, int layoutResId) {
        super(context, items, layoutResId);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, ProductSDeatilBody item) {
        // 产品图
        ImageView shop_tupian = holder.findViewById(R.id.shop_tupian);
        Picasso.with(mContext).load(item.logoUrl).placeholder(R.mipmap.morenproduct).into(shop_tupian);
        // 价格
        TextView shop_jiage = holder.findViewById(R.id.shop_jiage);
        shop_jiage.setText(item.productFormatPrice);
        // 规格
        TextView shop_guige = holder.findViewById(R.id.shop_guige);
        shop_guige.setText(item.formatName);
        //名称
        TextView shop_title = holder.findViewById(R.id.shop_title);
        shop_title.setText(item.productName);
        //数量  默认
        TextView shop_shuliang = holder.findViewById(R.id.shop_shuliang);
        if (null == item.count) {
            shop_shuliang.setText("X1");
        } else {

            shop_shuliang.setText("X" + item.count);
        }
    }
}
