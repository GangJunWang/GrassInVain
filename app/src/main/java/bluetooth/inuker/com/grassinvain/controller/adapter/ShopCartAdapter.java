package bluetooth.inuker.com.grassinvain.controller.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shaojun.widget.superAdapter.SuperAdapter;
import com.shaojun.widget.superAdapter.internal.SuperViewHolder;

import java.util.List;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.network.body.request.ShopCartBody;

/**
 * Created by Administrator on 2017/4/14 0014.
 */

public class ShopCartAdapter extends SuperAdapter<ShopCartBody>{
    /**
     * Constructor for single itemView type.
     *
     * @param context
     * @param items
     * @param layoutResId
     */
    private OnEditClickListener mOnEditClickListener;
    private OnResfreshListener mOnResfreshListener;
    private ImageView shop_xuze,shop_tupian;
    private TextView shop_title,shop_guige,shop_jiage,shop_daping,shop_xiaoping,shop_shuliang,shop_num;
    private LinearLayout shop_genggai;
    private ImageView shop_jian,shop_add;
    public ShopCartAdapter(Context context, List<ShopCartBody> items, int layoutResId) {
        super(context, items, layoutResId);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, final ShopCartBody item) {
        shop_xuze=holder.findViewById(R.id.shop_xuze);
        shop_tupian=holder.findViewById(R.id.shop_tupian);
        shop_title=holder.findViewById(R.id.shop_title);
        shop_guige=holder.findViewById(R.id.shop_guige);
        shop_jiage=holder.findViewById(R.id.shop_jiage);
        shop_daping=holder.findViewById(R.id.shop_daping);
        shop_xiaoping=holder.findViewById(R.id.shop_xiaoping);
        shop_shuliang=holder.findViewById(R.id.shop_shuliang);
        shop_num=holder.findViewById(R.id.shop_num);
        shop_genggai=holder.findViewById(R.id.shop_genggai);
        shop_jian=holder.findViewById(R.id.shop_jian);
        shop_add=holder.findViewById(R.id.shop_add);

        if (mOnResfreshListener != null) {
            boolean isSelect = false;

                if (!item.isCheckout()) {
                    isSelect = false;
                } else {
                    isSelect = true;
                }

            mOnResfreshListener.onResfresh(isSelect);
        }
        if (item.isCheckout()){
            shop_xuze.setImageResource(R.mipmap.xuanzhong);

        }else {
            shop_xuze.setImageResource(R.mipmap.weixuan);
        }

        shop_xuze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item.isCheckout()){
                    item.setCheckout(false);
                    notifyDataSetChanged();
                }else {
                    item.setCheckout(true);
                    notifyDataSetChanged();
                }
            }
        });





    }

    public interface OnEditClickListener {
        void onEditClick(int position, String cartid, int count);
    }

    public void setOnEditClickListener(OnEditClickListener mOnEditClickListener) {
        this.mOnEditClickListener = mOnEditClickListener;
    }

    public interface OnResfreshListener {
        void onResfresh(boolean isSelect);
    }

    public void setResfreshListener(OnResfreshListener mOnResfreshListener) {
        this.mOnResfreshListener = mOnResfreshListener;
    }

}
