package bluetooth.inuker.com.grassinvain.common.widget.ultra.loadmore;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.shaojun.utils.log.Logger;
import com.shaojun.widget.superAdapter.SuperAdapter;

import bluetooth.inuker.com.grassinvain.R;
import bluetooth.inuker.com.grassinvain.common.util.CommonUtil;
import bluetooth.inuker.com.grassinvain.common.util.MConstants;
import bluetooth.inuker.com.grassinvain.common.widget.ultra.PtrDefaultHandler;
import bluetooth.inuker.com.grassinvain.common.widget.ultra.PtrFrameLayout;
import bluetooth.inuker.com.grassinvain.common.widget.ultra.PtrHTFrameLayout;

/**
 * 逻辑视图，直播列表，可以切换显示视图（list/Grid）
 */
public class RLPtrFrameLayout extends RelativeLayout {

    private PtrHTFrameLayout ptrHTFrameLayout;
    private LRecyclerView lRecyclerView;
    private SuperAdapter superAdapter;
    private boolean isRefreshEnabled = true;
    //是否支持上拉加载开关
    private boolean isLoadMoreEnabled = false;

    private OnRefreshOrLoadMoreListener onRefreshOrLoadMoreListener;

    public interface OnRefreshOrLoadMoreListener {
        void onRefresh();

        void onLoadMore();
    }

    public void setOnRefreshOrLoadMoreListener(OnRefreshOrLoadMoreListener onRefreshOrLoadMoreListener) {
        this.onRefreshOrLoadMoreListener = onRefreshOrLoadMoreListener;
    }

    public RLPtrFrameLayout(Context context) {
        super(context);
        init(context);
    }

    public RLPtrFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RLPtrFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    //解决PtrFrameLayout嵌套ViewPager滑动冲突
    public void setViewPager(ViewPager viewPager) {
        ptrHTFrameLayout.setViewPager(viewPager);
    }

    public void autoRefresh() {
        ptrHTFrameLayout.autoRefresh(false);
    }

    public void refreshComplete() {
        ptrHTFrameLayout.refreshComplete();
        //加载完成更新试图
        lRecyclerView.changeLoadMoreState(LoadMoreFooterView.LOAD_MORE_STATE_COMPLETE);
    }

    public void loadMoreComplete() {
        lRecyclerView.changeLoadMoreState(LoadMoreFooterView.LOAD_MORE_STATE_COMPLETE);
    }

    public void init(Context context) {
        LayoutInflater.from(getContext()).inflate(R.layout.widget_refresh_loadmore, this);
        ptrHTFrameLayout = (PtrHTFrameLayout) findViewById(R.id.refresh_framentlayout);
        CommonUtil.SetPtrRefreshConfig(getContext(), ptrHTFrameLayout, MConstants.REFRESH_HEADER_WHITE);
        ptrHTFrameLayout.setPtrHandler(new PtrDefaultHandler() {

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                if (!isRefreshEnabled) {
                    return false;
                } else {
                    return super.checkCanDoRefresh(frame, content, header);
                }
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                if (onRefreshOrLoadMoreListener != null) {
                    //如果当前正在加载更多，忽略刷新
                    if (lRecyclerView.getLoadMoreState() != LoadMoreFooterView.LOAD_MORE_STATE_LOADING) {
                        //开启刷新，关闭上拉加载
                        onRefreshOrLoadMoreListener.onRefresh();
                        lRecyclerView.setLoadMoreEnabled(false);
                    } else {
                        Logger.d("正在加载更多，忽略刷新...");
                        ptrHTFrameLayout.refreshComplete();
                    }
                }
            }

        });
        lRecyclerView = (LRecyclerView) findViewById(R.id.loadmore_RecyclerView);
        lRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), OrientationHelper.VERTICAL, false));
        lRecyclerView.setLoadModeDataListener(new LRecyclerView.LoadModeDataListener() {
            @Override
            public void onLoadMore() {
                if (onRefreshOrLoadMoreListener != null) {
                    onRefreshOrLoadMoreListener.onLoadMore();
                }
            }

            @Override
            public void onRetry() {
                if (onRefreshOrLoadMoreListener != null) {
                    onRefreshOrLoadMoreListener.onLoadMore();
                }
            }
        });

    }


    public void setRefreshEnabled(boolean refreshEnabled) {
        isRefreshEnabled = refreshEnabled;
    }

    public RecyclerView getRecyclerView() {
        return lRecyclerView;
    }

    public void setAdapter(SuperAdapter superAdapter, boolean isLoadMoreEnabled) {
        this.superAdapter = superAdapter;
        if (isLoadMoreEnabled) {
            lRecyclerView.setAdapter(superAdapter, true);
        } else {
            lRecyclerView.setAdapter(superAdapter);

        }
    }

    public void switchType(int showType) {
        lRecyclerView.switchType(showType);
    }

    public void changeLoadMoreState(int loadMoreState) {
        lRecyclerView.changeLoadMoreState(loadMoreState);
    }

}