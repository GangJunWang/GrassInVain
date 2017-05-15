package bluetooth.inuker.com.grassinvain.controller.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.shaojun.widget.superAdapter.SuperAdapter;
import com.shaojun.widget.superAdapter.internal.SuperViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.common.util.TextUtil;
import bluetooth.inuker.com.grassinvain.network.body.response.AllOrdersecondBody;

/**
 * Created by 1 on 2017/4/19.
 */

public class WaitPayAdapter extends SuperAdapter<AllOrdersecondBody> {
    /**
     * Constructor for single itemView type.
     *
     * @param context
     * @param items
     * @param layoutResId
     */
    public WaitPayAdapter(Context context, List<AllOrdersecondBody> items, int layoutResId) {
        super(context, items, layoutResId);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, AllOrdersecondBody item) {
        // 产品图
        ImageView shop_tupian = holder.findViewById(R.id.shop_tupian);
        Picasso.with(mContext).load(item.logoUrl).placeholder(R.mipmap.morenproduct).into(shop_tupian);
        // 价格
        TextView shop_jiage = holder.findViewById(R.id.shop_jiage);
        if (TextUtil.checkEmpty(item.price)) {
            String price = item.price;
            shop_jiage.setText(item.price.substring(0,price.length()-2));
        }

        // 规格
        TextView shop_guige = holder.findViewById(R.id.shop_guige);
        shop_guige.setText(item.productFormatId);
        //名称
        TextView shop_title = holder.findViewById(R.id.shop_title);
        shop_title.setText(item.productName);
        //数量  默认
        TextView shop_shuliang = holder.findViewById(R.id.shop_shuliang);
        shop_shuliang.setText("X" + item.count);
    }

}
