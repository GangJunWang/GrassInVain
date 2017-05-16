package bluetooth.inuker.com.grassinvain.controller.adapter;

import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shaojun.widget.superAdapter.SuperAdapter;
import com.shaojun.widget.superAdapter.internal.SuperViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.network.body.request.JoinShoppingCarBody;
import bluetooth.inuker.com.grassinvain.network.body.response.ProductListBody;
import bluetooth.inuker.com.grassinvain.network.model.UserModel;
import bluetooth.inuker.com.grassinvain.network.model.callback.Callback;

/**
 * Created by Super-me on 2017/3/25.
 */

public class HomeAdapter extends SuperAdapter<ProductListBody> {

    private UserModel userModel;

    /**
     * Constructor for single itemView type.
     *
     * @param context
     * @param items
     * @param layoutResId
     */
    public HomeAdapter(Context context, List<ProductListBody> items, int layoutResId) {
        super(context, items, layoutResId);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, final int layoutPosition, final ProductListBody item) {
        // 网络请求
        userModel = new UserModel(mContext);
        // 商品名称
        TextView product_name = holder.findViewById(R.id.product_name);
        //赋值
        product_name.setText(item.productName);
        //商品价格
        TextView product_price = holder.findViewById(R.id.product_price);
        //赋值
        product_price.setText(item.price + "");
        //商品图标
        ImageView product_image = holder.findViewById(R.id.product_image);
        if (null != item.logoUrl && !"".equals(item.logoUrl)) {
            Picasso.with(mContext).load(item.logoUrl)
                    .placeholder(R.mipmap.chanpintu).into(product_image);
        }
        // 赋值
        final LinearLayout bigType = holder.findViewById(R.id.big_type);
        final LinearLayout smallType = holder.findViewById(R.id.small_type);
        final ImageView joinShoping = holder.findViewById(R.id.join_shoping);
        /**
         * 点击事件   加入购物车    选择规格
         */
        joinShoping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* if (chooseType != null){
                    chooseType.chooseType(layoutPosition);
                }*/
                smallType.setVisibility(View.VISIBLE);
                smallType.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.endtotop));
                bigType.setVisibility(View.VISIBLE);
                bigType.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.toptoend1));

            }
        });

        bigType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* if (chooseType != null) {
                    chooseType.chooseBig(layoutPosition);
                }*/
                JoinShoppingCarBody joinShoppingCarBody = new JoinShoppingCarBody();
                joinShoppingCarBody.productId = item.productId + "";
                joinShoppingCarBody.formatId = "2";
                userModel.getJoinShoppingCar(joinShoppingCarBody, new Callback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        Toast.makeText(mContext, "加入购物车", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(int resultCode, String message) {

                    }
                });
                smallType.setVisibility(View.INVISIBLE);
                smallType.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.toptoend));
                bigType.setVisibility(View.INVISIBLE);
                bigType.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.endtotop1));

            }
        });
        smallType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (chooseType != null) {
                    chooseType.chooseSmall(layoutPosition);
                }*/
                JoinShoppingCarBody joinShoppingCarBody = new JoinShoppingCarBody();
                joinShoppingCarBody.productId = item.productId + "";
                joinShoppingCarBody.formatId = "1";
                userModel.getJoinShoppingCar(joinShoppingCarBody, new Callback<String>() {
                    @Override
                    public void onSuccess(String s) {
                        Toast.makeText(mContext, "加入购物车", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onFailure(int resultCode, String message) {

                    }
                });
                smallType.setVisibility(View.INVISIBLE);
                smallType.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.toptoend));
                bigType.setVisibility(View.INVISIBLE);
                bigType.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.endtotop1));
            }
        });


    }

    public ChooseType chooseType;

    public void setOnItemClickListener(ChooseType chooseType) {
        this.chooseType = chooseType;
    }

    public interface ChooseType {

        void chooseType(int positon);

        void chooseBig(int position);

        void chooseSmall(int position);
    }

}
