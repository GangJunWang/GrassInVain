package bluetooth.inuker.com.grassinvain.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.loonggg.rvbanner.lib.RecyclerViewBanner;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.common.cache.CacheCenter;
import bluetooth.inuker.com.grassinvain.controller.MainActivity;
import bluetooth.inuker.com.grassinvain.controller.adapter.ProductDetailAdapter;
import bluetooth.inuker.com.grassinvain.controller.login.LoginActivity;
import bluetooth.inuker.com.grassinvain.network.body.request.JoinShoppingCarBody;
import bluetooth.inuker.com.grassinvain.network.body.response.PageBody;
import bluetooth.inuker.com.grassinvain.network.body.response.ProductPersonBody;
import bluetooth.inuker.com.grassinvain.network.body.response.ProductSDeatilBody;
import bluetooth.inuker.com.grassinvain.network.body.response.ProductSpeakList;
import bluetooth.inuker.com.grassinvain.network.body.response.ProductSpreakBody;
import bluetooth.inuker.com.grassinvain.network.model.UserModel;
import bluetooth.inuker.com.grassinvain.network.model.callback.Callback;

public class GoodsDetails extends AppCompatActivity implements View.OnClickListener {

    private TextView goumai;
    private TextView jiarugouwuche;
    private RecyclerViewBanner banner1;
    private List<Banner> banner = new ArrayList<>();
    private TabLayout tablayoutShangpin;
    private ImageView finishBack;
    private FrameLayout frameLayout;
    private RecyclerView pinglun_recycView;
    private TextView shangpinpingjia;
    private ScrollView detailScrollView;
    private String productsId;
    private UserModel userModel;
    private List<ProductSDeatilBody> productSDeatilBodies1;
    private TextView product_name;
    private TextView product_price;
    private List<ProductPersonBody> data = new ArrayList<>();
    private TextView detail_big;
    private TextView detail_small;
    private String productGuigeId;
    private List<ProductSDeatilBody> list = new ArrayList<>();
    private ProductSpeakList productSpeakList1;
    private ProductDetailAdapter productDetailAdapter;
    //商品评价的页码
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);
        userModel = new UserModel(this);
        Intent intent = getIntent();
        productsId = intent.getStringExtra("result");
        initData(page);
    }

    private void initData(int page) {

        int i = Integer.parseInt(productsId);
        PageBody pageBody = new PageBody();
        pageBody.pageNum = page;
        pageBody.pageSize = 20;
        pageBody.orders = "";
        userModel.getPSpeakList(i, pageBody, new Callback<ProductSpreakBody>() {
            @Override
            public void onSuccess(ProductSpreakBody productSpreakBody) {
                productDetailAdapter.clear();
                data.clear();
                List<ProductPersonBody> list = productSpreakBody.list;
                data.addAll(list);
                productDetailAdapter.addAll(list);
                productDetailAdapter.notifyDataSetHasChanged();

            }

            @Override
            public void onFailure(int resultCode, String message) {

            }
        });

        userModel.getProductDetail(i, new Callback<List<ProductSDeatilBody>>() {
            @Override
            public void onSuccess(List<ProductSDeatilBody> productSDeatilBodies) {
                //开始装配数据
                productSDeatilBodies1 = productSDeatilBodies;
                initView();
            }

            @Override
            public void onFailure(int resultCode, String message) {

            }
        });
    }

    private void initView() {

        //返回
        finishBack = (ImageView) findViewById(R.id.finish_back);
        finishBack.setOnClickListener(this);
        //进入购物车
        frameLayout = (FrameLayout) findViewById(R.id.framlayout_image);
        frameLayout.setOnClickListener(this);
        //  轮播
        banner.add(new Banner(productSDeatilBodies1.get(0).logoUrl));
        banner1 = (RecyclerViewBanner) findViewById(R.id.banner1);
        banner1.setRvBannerData(banner);
        banner1.setIndicatorInterval(3000);
        banner1.setOnSwitchRvBannerListener(new RecyclerViewBanner.OnSwitchRvBannerListener() {
            @Override
            public void switchBanner(int i, AppCompatImageView appCompatImageView) {
                Picasso.with(GoodsDetails.this).load(banner.get(i).getUrl())
                        .placeholder(R.mipmap.chanpintu).into(appCompatImageView);
            }
        });
        tablayoutShangpin = (TabLayout) findViewById(R.id.tablayout_shangpin);
        tablayoutShangpin.addTab(tablayoutShangpin.newTab().setText("商品详情"));
        tablayoutShangpin.addTab(tablayoutShangpin.newTab().setText("商品评价"));
        tablayoutShangpin.setTabMode(tablayoutShangpin.MODE_FIXED);

        tablayoutShangpin.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (0 == tab.getPosition()) {
                    shangpinpingjia.setVisibility(View.VISIBLE);
                    pinglun_recycView.setVisibility(View.GONE);
                }
                if (1 == tab.getPosition()) {
                    shangpinpingjia.setVisibility(View.GONE);
                    pinglun_recycView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //商品名字
        product_name = (TextView) findViewById(R.id.product_name);
        //商品价格
        product_price = (TextView) findViewById(R.id.product_price);

        //  选择大瓶
        detail_big = (TextView) findViewById(R.id.detail_big);
        detail_big.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                detail_big.setTextColor(GoodsDetails.this.getResources().getColor(R.color.write));
                detail_big.setBackgroundResource(R.drawable.back_biankuang);
                detail_small.setTextColor(GoodsDetails.this.getResources().getColor(R.color.black));
                detail_small.setBackgroundResource(R.drawable.back_biankuang_weite);
                product_price.setText(productSDeatilBodies1.get(1).productFormatPrice);
                productGuigeId = productSDeatilBodies1.get(1).productFormatId;
            }
        });
        // 选择小瓶
        detail_small = (TextView) findViewById(R.id.detail_small);
        detail_small.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detail_big.setTextColor(GoodsDetails.this.getResources().getColor(R.color.black));
                detail_big.setBackgroundResource(R.drawable.back_biankuang_weite);
                detail_small.setTextColor(GoodsDetails.this.getResources().getColor(R.color.write));
                detail_small.setBackgroundResource(R.drawable.back_biankuang);
                product_price.setText(productSDeatilBodies1.get(0).productFormatPrice);
                productGuigeId = productSDeatilBodies1.get(0).productFormatId;

            }
        });

        product_name.setText(productSDeatilBodies1.get(0).productName);
        product_price.setText(productSDeatilBodies1.get(0).productFormatPrice);
        // 立即购买
        goumai = (TextView) findViewById(R.id.goumai);
        goumai.setOnClickListener(this);
        // 加入购物车
        jiarugouwuche = (TextView) findViewById(R.id.jiarugouwuche);
        jiarugouwuche.setOnClickListener(this);

        //商品详情
        shangpinpingjia = (TextView) findViewById(R.id.shangpinpingjia);
        shangpinpingjia.setText("        " + productSDeatilBodies1.get(0).productDesc);

        //商品评价
        pinglun_recycView = (RecyclerView) findViewById(R.id.pinglun_recycView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        };
        pinglun_recycView.setLayoutManager(linearLayoutManager);
        productDetailAdapter = new ProductDetailAdapter(getBaseContext(), data, R.layout.activity_shangpindetail_contont);
        pinglun_recycView.setAdapter(productDetailAdapter);
        detailScrollView = (ScrollView) findViewById(R.id.Detail_ScroView);
        detailScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // 监听滑动到头部与底部
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_MOVE: {
                        if (detailScrollView.getScrollY() <= 0) {
                            // 说明没有滑动，在屏幕顶部
                        }
                        int measuredHeight = detailScrollView.getChildAt(0)
                                .getMeasuredHeight();
                        int scrollY = detailScrollView.getScrollY();
                        int height = detailScrollView.getHeight();
                        if (measuredHeight <= scrollY + height) {
                            // 已经滑动的距离+在屏幕上显示的高度>=控件真实高度。说明已经滑动到底部
                            //此时在底部  应该加载更多的数据
                        //    initData(page++);
                        }
                        break;
                    }
                    default:
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.finish_back:
                finish();
                break;
            case R.id.framlayout_image:
                Intent intent = new Intent(GoodsDetails.this, MainActivity.class);
                intent.putExtra("GOODDETAILS", "2");
                startActivity(intent);
                finish();
                break;
            //加入购物车
            case R.id.jiarugouwuche:
                if (!CacheCenter.getInstance().isLogin()) {
                    Toast.makeText(getBaseContext(), "请先登录", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(GoodsDetails.this, LoginActivity.class);
                    startActivity(intent1);
                } else {
                    JoinShoppingCarBody joinShoppingCarBody = new JoinShoppingCarBody();
                    String productsId = productSDeatilBodies1.get(0).productId;
                    String productsFormatId = productGuigeId;
                    joinShoppingCarBody.formatId = productsFormatId;
                    joinShoppingCarBody.productId = productsId;
                    userModel.getJoinShoppingCar(joinShoppingCarBody, new Callback<String>() {
                        @Override
                        public void onSuccess(String baseResult) {
                            Toast.makeText(getBaseContext(), "添加成功", Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onFailure(int resultCode, String message) {
                            Toast.makeText(getBaseContext(), "添加失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                break;
            case R.id.goumai:
                if (!CacheCenter.getInstance().isLogin()) {
                    Toast.makeText(getBaseContext(), "请先登录", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(GoodsDetails.this, LoginActivity.class);
                    startActivity(intent1);
                } else {
                    Intent intent1 = new Intent(GoodsDetails.this, OrderActivity.class);

                    if (product_price.getText().toString().equals(productSDeatilBodies1.get(0).productFormatPrice)) {
                        list.add(productSDeatilBodies1.get(0));
                        intent1.putExtra("product", (Serializable) list);
                    }
                    if (product_price.getText().toString().equals(productSDeatilBodies1.get(1).productFormatPrice)) {
                        list.add(productSDeatilBodies1.get(1));
                        intent1.putExtra("product", (Serializable) list);
                    }
                    startActivity(intent1);
                }
                break;
        }
    }

    private class Banner {
        String url;
        public Banner(String url) {
            this.url = url;
        }
        public String getUrl() {
            return url;
        }
    }
}
