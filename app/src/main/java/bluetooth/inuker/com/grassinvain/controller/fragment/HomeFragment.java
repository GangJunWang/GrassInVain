package bluetooth.inuker.com.grassinvain.controller.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import com.loonggg.rvbanner.lib.RecyclerViewBanner;
import com.shaojun.widget.superAdapter.OnItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.controller.activity.GoodsDetails;
import bluetooth.inuker.com.grassinvain.controller.adapter.HomeAdapter;
import bluetooth.inuker.com.grassinvain.network.body.response.BannerBody;
import bluetooth.inuker.com.grassinvain.network.body.response.PageBody;
import bluetooth.inuker.com.grassinvain.network.body.response.ProductBody;
import bluetooth.inuker.com.grassinvain.network.body.response.ProductListBody;
import bluetooth.inuker.com.grassinvain.network.model.UserModel;
import bluetooth.inuker.com.grassinvain.network.model.callback.Callback;


/**
 * Created by Super-me on 2017/3/25.
 */

public class HomeFragment extends Fragment {

    private View view;
    private RecyclerView homeRecyclerview;
    private List<ProductListBody> data = new ArrayList<>();
    private HomeAdapter homeAdapter;
    private ScrollView topScrollview;
    private RecyclerViewBanner banner;
    private List<Banner> listBanner = new ArrayList<>();
    private UserModel userModel;
    private ProductBody productListBodies1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.hone_activity, null);
        userModel = new UserModel(getActivity());
        initData();
        return view;
    }

    /**
     * 再次获取焦点
     */
    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * 失去焦点
     */
    @Override
    public void onPause() {
        super.onPause();
        //   data.clear();
        // listBanner.clear();
    }
    private void initData() {
        PageBody pageBody = new PageBody();
        pageBody.pageNum = 1;
        pageBody.pageSize = 10;
        pageBody.orders = "";
        userModel.getProductList(pageBody, new Callback<ProductBody>() {
            @Override
            public void onSuccess(ProductBody productListBodies) {
                productListBodies1 = productListBodies;
                List<ProductListBody> list = productListBodies.list;
                data.addAll(list);
                homeAdapter.clear();
                homeAdapter.addAll(list);
                homeAdapter.notifyDataSetHasChanged();
            }
            @Override
            public void onFailure(int resultCode, String message) {
            }
        });
        userModel.getBanner(new Callback<List<BannerBody>>() {
            @Override
            public void onSuccess(List<BannerBody> banners) {
                listBanner.clear();
                for (int i = 0; i < banners.size(); i++) {
                    listBanner.add(new Banner(banners.get(i).resource));
                }
                initView();
            }
            @Override
            public void onFailure(int resultCode, String message) {
            }
        });
    }

    private void initView() {

        banner = (RecyclerViewBanner) view.findViewById(R.id.shouye_lunbo);
        banner.setRvBannerData(listBanner);
        banner.setIndicatorInterval(2000);
        banner.setOnSwitchRvBannerListener(new RecyclerViewBanner.OnSwitchRvBannerListener() {
            @Override
            public void switchBanner(int i, AppCompatImageView appCompatImageView) {
                Picasso.with(getActivity()).load(listBanner.get(i).getUrl())
                        .placeholder(R.mipmap.banner).into(appCompatImageView);
            }
        });
        banner.setOnRvBannerClickListener(new RecyclerViewBanner.OnRvBannerClickListener() {
            @Override
            public void onClick(int i) {
                Toast.makeText(getActivity(), "" + i, Toast.LENGTH_SHORT).show();
            }
        });


        homeRecyclerview = (RecyclerView) view.findViewById(R.id.home_recyclerview);
        topScrollview = (ScrollView) view.findViewById(R.id.top_scrollview);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                topScrollview.fullScroll(ScrollView.FOCUS_UP);
            }
        }, 100);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        homeRecyclerview.setLayoutManager(gridLayoutManager);
        homeAdapter = new HomeAdapter(getActivity(), data, R.layout.home_content_activity);
        homeRecyclerview.setAdapter(homeAdapter);

        topScrollview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_MOVE: {
                        if (topScrollview.getScrollY() <= 0) {
                        }
                        int measuredHeight = topScrollview.getChildAt(0)
                                .getMeasuredHeight();
                        int scrollY = topScrollview.getScrollY();
                        int height = topScrollview.getHeight();
                        if (measuredHeight <= scrollY + height) {
                            //loadMoredata();
                        }
                        break;
                    }
                    default:
                        break;
                }
                return false;
            }
        });
        homeAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int viewType, int position) {
                Long productsId = productListBodies1.list.get(position).productId;
                Intent intent = new Intent(getActivity(), GoodsDetails.class);
                intent.putExtra("result", productsId + "");
                startActivity(intent);
            }
        });
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
