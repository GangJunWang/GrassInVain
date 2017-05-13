package bluetooth.inuker.com.grassinvain.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.network.body.request.ShopCartBody;

/**
 * Created by Administrator on 2017/4/15 0015.
 */

public class ShopCart2Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<ShopCartBody> list = new ArrayList<>();
    private Context context;
    private OnResfreshListener mOnResfreshListener;

    public ShopCart2Adapter(List<ShopCartBody> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shopping_cart, parent,false);

        return new ShopCartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final ShopCartBody shopCartBody = list.get(position);
        if (mOnResfreshListener != null) {
            boolean isSelect = false;
            for (int i = 0; i < list.size(); i++) {
                if (!list.get(i).isCheckout()) {
                    isSelect = false;
                    break;
                } else {
                    isSelect = true;
                }
            }
            mOnResfreshListener.onResfresh(isSelect);
        }
        //是否被选中
        if (shopCartBody.isCheckout()) {
            ((ShopCartViewHolder) holder).shop_xuze.setImageResource(R.mipmap.xuanzhong);

        } else {
            ((ShopCartViewHolder) holder).shop_xuze.setImageResource(R.mipmap.weixuan);
        }
        //是否是操作
        if (shopCartBody.isModify()) {
            ((ShopCartViewHolder) holder).shop_shuliang.setVisibility(View.GONE);
            ((ShopCartViewHolder) holder).shop_genggai.setVisibility(View.VISIBLE);
            ((ShopCartViewHolder) holder).shop_guige.setBackgroundResource(R.mipmap.recharge_unselected);
            //为规格设置点击事件
            ((ShopCartViewHolder) holder).shop_guige.setEnabled(true);
            ((ShopCartViewHolder) holder).shop_guige.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((ShopCartViewHolder) holder).shop_guige.setVisibility(View.GONE);
                    ((ShopCartViewHolder) holder).shop_jiage.setVisibility(View.GONE);
                    ((ShopCartViewHolder) holder).shop_daping.setVisibility(View.VISIBLE);
                    ((ShopCartViewHolder) holder).shop_xiaoping.setVisibility(View.VISIBLE);
                    shopCartBody.setCheckout(true);
                    ((ShopCartViewHolder) holder).shop_xuze.setImageResource(R.mipmap.xuanzhong);
                }
            });
        } else {
            ((ShopCartViewHolder) holder).shop_shuliang.setVisibility(View.VISIBLE);
            ((ShopCartViewHolder) holder).shop_genggai.setVisibility(View.GONE);
            ((ShopCartViewHolder) holder).shop_guige.setBackgroundColor(0x00ffffff);
            ((ShopCartViewHolder) holder).shop_guige.setEnabled(false);
        }

        ((ShopCartViewHolder) holder).shop_daping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ShopCartViewHolder) holder).shop_daping.setVisibility(View.GONE);
                ((ShopCartViewHolder) holder).shop_xiaoping.setVisibility(View.GONE);
                ((ShopCartViewHolder) holder).shop_guige.setVisibility(View.VISIBLE);
                ((ShopCartViewHolder) holder).shop_jiage.setVisibility(View.VISIBLE);
                list.get(position).formatName=list.get(position).productFormatList.get(1).formatName;
                list.get(position).formatPrice=list.get(position).productFormatList.get(1).price;
                list.get(position).productId=list.get(position).productFormatList.get(1).productId;
                list.get(position).logoUrl=list.get(position).productFormatList.get(1).logoUrl;
                notifyDataSetChanged();

            }
        });
        ((ShopCartViewHolder) holder).shop_xiaoping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ShopCartViewHolder) holder).shop_daping.setVisibility(View.GONE);
                ((ShopCartViewHolder) holder).shop_xiaoping.setVisibility(View.GONE);
                ((ShopCartViewHolder) holder).shop_guige.setVisibility(View.VISIBLE);
                ((ShopCartViewHolder) holder).shop_jiage.setVisibility(View.VISIBLE);
                list.get(position).formatName=list.get(position).productFormatList.get(0).formatName;
                list.get(position).formatPrice=list.get(position).productFormatList.get(0).price;
                list.get(position).productId=list.get(position).productFormatList.get(0).productId;
                list.get(position).logoUrl=list.get(position).productFormatList.get(0 ).logoUrl;
                notifyDataSetChanged();
            }
        });

        ((ShopCartViewHolder) holder).shop_xuze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shopCartBody.isCheckout()) {
                    shopCartBody.setCheckout(false);
                    notifyDataSetChanged();
                } else {
                    shopCartBody.setCheckout(true);
                    notifyDataSetChanged();
                }
            }
        });

        ((ShopCartViewHolder) holder).shop_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position).count.equals("1")){
                    shopCartBody.setCheckout(true);
                    return;
                }else {
                    String num = list.get(position).count;
                    int i = Integer.parseInt(num)-1;
                    list.get(position).count=i+"";
                    shopCartBody.setCheckout(true);
                    notifyDataSetChanged();
                }
            }
        });

       ((ShopCartViewHolder) holder).shop_shuliang.setText("X"+list.get(position).count);
        Picasso.with(context).load(list.get(position).logoUrl)
                .placeholder(R.mipmap.morenproduct).into(((ShopCartViewHolder) holder).shop_tupian);
        ((ShopCartViewHolder) holder).shop_guige.setText(list.get(position).formatName);
        ((ShopCartViewHolder) holder).shop_num.setText(list.get(position).count);
        ((ShopCartViewHolder) holder).shop_jiage.setText(list.get(position).formatPrice.substring(0,list.get(position).formatPrice.length()-2));
        ((ShopCartViewHolder) holder).shop_title.setText(list.get(position).productName);

        ((ShopCartViewHolder) holder).shop_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = list.get(position).count;
                int i = Integer.parseInt(num)+1;
                list.get(position).count=i+"";
                shopCartBody.setCheckout(true);
                notifyDataSetChanged();
            }

        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public interface OnResfreshListener {
        void onResfresh(boolean isSelect);
    }

    public void setResfreshListener(OnResfreshListener mOnResfreshListener) {
        this.mOnResfreshListener = mOnResfreshListener;
    }


    class ShopCartViewHolder extends RecyclerView.ViewHolder{
        private ImageView shop_xuze,shop_tupian;
        private TextView shop_title,shop_guige,shop_jiage,shop_daping,shop_xiaoping,shop_shuliang,shop_num;
        private LinearLayout shop_genggai;
        private TextView shop_jian,shop_add;

        public ShopCartViewHolder(View itemView) {
            super(itemView);
            shop_xuze= (ImageView) itemView.findViewById(R.id.shop_xuze);
            shop_tupian= (ImageView) itemView.findViewById(R.id.shop_tupian);
            shop_title= (TextView) itemView.findViewById(R.id.shop_title);
            shop_guige= (TextView) itemView.findViewById(R.id.shop_guige);
            shop_jiage= (TextView) itemView.findViewById(R.id.shop_jiage);
            shop_daping= (TextView) itemView.findViewById(R.id.shop_daping);
            shop_xiaoping= (TextView) itemView.findViewById(R.id.shop_xiaoping);
            shop_shuliang= (TextView) itemView.findViewById(R.id.shop_shuliang);
            shop_num= (TextView) itemView.findViewById(R.id.shop_num);
            shop_genggai= (LinearLayout) itemView.findViewById(R.id.shop_genggai);
            shop_jian= (TextView) itemView.findViewById(R.id.shop_jian);
            shop_add= (TextView) itemView.findViewById(R.id.shop_add);
        }
    }
}
