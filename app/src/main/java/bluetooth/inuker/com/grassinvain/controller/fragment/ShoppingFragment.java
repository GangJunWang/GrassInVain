package bluetooth.inuker.com.grassinvain.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.controller.MainActivity;
import bluetooth.inuker.com.grassinvain.controller.activity.OrderActivity;
import bluetooth.inuker.com.grassinvain.controller.adapter.ShopCart2Adapter;
import bluetooth.inuker.com.grassinvain.controller.personinformation.ShoppingCartListenter;
import bluetooth.inuker.com.grassinvain.network.body.request.ShopCartBody;
import bluetooth.inuker.com.grassinvain.network.body.response.PageBody;
import bluetooth.inuker.com.grassinvain.network.body.response.ProductSDeatilBody;
import bluetooth.inuker.com.grassinvain.network.body.response.ShopCarListBody;
import bluetooth.inuker.com.grassinvain.network.model.UserModel;
import bluetooth.inuker.com.grassinvain.network.model.callback.Callback;

/**
 * Created by Super-me on 2017/3/25.
 */

public class ShoppingFragment extends Fragment implements ShoppingCartListenter, View.OnClickListener {

    private RecyclerView shoppingCart_recyclerView;
    private ImageView shoppingCart_quanxuan;
    private TextView shoppingCart_zongji, shoppingCart_rmb, shoppingCart_zongjia;
    private Button shoppingCart_jiesuan;
    private ShopCart2Adapter shopCartAdapter;
    private boolean mSelect;
    private ShopCartBody shopCartBody;
    private float mTotalPrice1;
    private UserModel userModel;
    private List<ShopCartBody> list;
    private List<ProductSDeatilBody> productSDeatilBodyList = new ArrayList<>();
    private ProductSDeatilBody productSDeatilBody;
    private List listprodect = new ArrayList();
    private RelativeLayout zanwushangpin;
    private TextView change_firstFragment;
    private RelativeLayout quanbuyincang;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gouewuche_activity, null);
        userModel = new UserModel(getActivity());
        return view;
    }

    private void initData() {

        PageBody pageBody = new PageBody();
        pageBody.pageSize = 20;
        pageBody.pageNum = 1;
        userModel.getShopCarList(pageBody, new Callback<ShopCarListBody>() {
            @Override
            public void onSuccess(ShopCarListBody shopCarListBody) {
                list = shopCarListBody.list;
                if (0 != list.size()) {
                    quanbuyincang.setVisibility(View.VISIBLE);
                    zanwushangpin.setVisibility(View.GONE);
                }
                if (0 == list.size()) {
                    zanwushangpin.setVisibility(View.VISIBLE);
                    quanbuyincang.setVisibility(View.GONE);
                }
                initRecyclerView();
            }

            @Override
            public void onFailure(int resultCode, String message) {
            }
        });

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();

    }

    private void initRecyclerView() {

        shoppingCart_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        shopCartAdapter = new ShopCart2Adapter(list, getActivity());
        shoppingCart_recyclerView.setAdapter(shopCartAdapter);
        //监控全选
        shopCartAdapter.setResfreshListener(new ShopCart2Adapter.OnResfreshListener() {
            @Override
            public void onResfresh(boolean isSelect) {
                mSelect = isSelect;
                if (isSelect) {
                    shoppingCart_quanxuan.setImageResource(R.mipmap.xuanzhong);
                } else {
                    shoppingCart_quanxuan.setImageResource(R.mipmap.weixuan);
                }
                float mTotalPrice = 0;
                mTotalPrice1 = 0;
                if (productSDeatilBodyList != null) {
                    productSDeatilBodyList.clear();
                }
                for (int i = 0; i < list.size(); i++)
                    if (list.get(i).isCheckout()) {
                        mTotalPrice += Integer.parseInt(list.get(i).formatPrice) * Integer.parseInt(list.get(i).count);
                        productSDeatilBody = new ProductSDeatilBody();
                        productSDeatilBody.count = list.get(i).count;
                        productSDeatilBody.formatName = list.get(i).formatName;
                        productSDeatilBody.productName = list.get(i).productName;
                        productSDeatilBody.logoUrl = list.get(i).logoUrl;
                        productSDeatilBody.productId = list.get(i).productId;
                        productSDeatilBody.productFormatPrice = list.get(i).formatPrice;
                        productSDeatilBodyList.add(productSDeatilBody);
                    }
                mTotalPrice1 = mTotalPrice;
                shoppingCart_zongjia.setText("" + mTotalPrice);
                if (shoppingCart_jiesuan.getText().toString().trim().equals("删除")) {

                } else {
                    int num = 0;
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).isCheckout()) {
                            num++;

                        }
                    }
                    shoppingCart_jiesuan.setText("结算（" + num + "）");

                }
            }
        });

    }

    private void initView() {
        /**
         * 默认背景
         */
        zanwushangpin = (RelativeLayout) getView().findViewById(R.id.zanwushuju);
        /**
         * 底部选项卡
         */
        quanbuyincang = (RelativeLayout) getView().findViewById(R.id.quanbuyincang);
        /**
         * 购物车暂无商品  切换到首页
         */
        change_firstFragment = (TextView) getView().findViewById(R.id.change_firstFragment);
        change_firstFragment.setOnClickListener(this);
        shoppingCart_recyclerView = (RecyclerView) getView().findViewById(R.id.shoppingCart_recyclerView);
        shoppingCart_quanxuan = (ImageView) getView().findViewById(R.id.shoppingCart_quanxuan);
        shoppingCart_quanxuan.setOnClickListener(this);
        shoppingCart_zongji = (TextView) getView().findViewById(R.id.shoppingCart_zongji);
        shoppingCart_rmb = (TextView) getView().findViewById(R.id.shoppingCart_rmb);
        shoppingCart_zongjia = (TextView) getView().findViewById(R.id.shoppingCart_zongjia);
        shoppingCart_jiesuan = (Button) getView().findViewById(R.id.shoppingCart_jiesuan);
        shoppingCart_jiesuan.setOnClickListener(this);
    }

    //操作
    @Override
    public void operation() {
        shoppingCart_zongji.setVisibility(View.GONE);
        shoppingCart_rmb.setVisibility(View.GONE);
        shoppingCart_zongjia.setVisibility(View.GONE);
        shoppingCart_jiesuan.setText("删除");
        shoppingCart_jiesuan.setBackgroundResource(R.color.color_red_pay);
        modify(true);
        shopCartAdapter.notifyDataSetChanged();
    }


    //完成操作
    @Override
    public void complete() {
        shoppingCart_zongji.setVisibility(View.VISIBLE);
        shoppingCart_rmb.setVisibility(View.VISIBLE);
        shoppingCart_zongjia.setVisibility(View.VISIBLE);
        int num = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isCheckout()) {
                num++;
            }
        }
        shoppingCart_jiesuan.setBackgroundResource(R.color.zhuticolor);
        shoppingCart_jiesuan.setText("结算（" + num + "）");
        modify(false);
        shopCartAdapter.notifyDataSetChanged();
    }

    private void modify(boolean b) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setModify(b);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // 切换到首页商品列表
            case R.id.change_firstFragment:
                MainActivity activity = (MainActivity) getActivity();
                activity.changeFragment();
                break;
            // 全部选择
            case R.id.shoppingCart_quanxuan:
                mSelect = !mSelect;
                if (mSelect) {
                    shoppingCart_quanxuan.setImageResource(R.mipmap.xuanzhong);
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setCheckout(true);
                    }
                } else {
                    shoppingCart_quanxuan.setImageResource(R.mipmap.weixuan);
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setCheckout(false);
                    }
                }
                shopCartAdapter.notifyDataSetChanged();
                break;
            // 去结算
            case R.id.shoppingCart_jiesuan:
                if (shoppingCart_jiesuan.getText().toString().trim().equals("删除")) {

                    if (0 != productSDeatilBodyList.size()) {
                        Toast.makeText(getActivity(), "删除", Toast.LENGTH_SHORT).show();
                        if (mSelect) {
                            list.clear();
                        } else {
                            digui();
                        }
                        shopCartAdapter.notifyDataSetChanged();
                        userModel.getdeleteProduct(listprodect, new Callback<Object>() {
                            @Override
                            public void onSuccess(Object o) {

                            }

                            @Override
                            public void onFailure(int resultCode, String message) {

                            }
                        });
                    } else {
                        Toast.makeText(getActivity(), "没有选中的产品", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    if (0 != productSDeatilBodyList.size()) {
                        Intent intent1 = new Intent(getActivity(), OrderActivity.class);
                        intent1.putExtra("product", (Serializable) productSDeatilBodyList);
                        startActivity(intent1);
                    } else {
                        Toast.makeText(getActivity(), "没有选中的产品", Toast.LENGTH_SHORT).show();
                    }

                }

                break;
        }


    }

    private void digui() {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isCheckout()) {
                listprodect.add(list.get(i).formatId);
                list.remove(i);
                digui();
            }
        }
    }


}
