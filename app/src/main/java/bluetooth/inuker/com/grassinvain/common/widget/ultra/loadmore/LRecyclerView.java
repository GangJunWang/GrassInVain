package bluetooth.inuker.com.grassinvain.common.widget.ultra.loadmore;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.shaojun.utils.log.Logger;
import com.shaojun.widget.superAdapter.SuperAdapter;

import bluetooth.inuker.com.grassinvain.common.util.MConstants;


/**
 * 支持上拉加载的RecyclerView
 */
public class LRecyclerView extends RecyclerView {


    //布局管理器
    private int layoutManagerType = -1;
    // 最后一个的位置
    private int[] lastPositions;
    // 最后一个可见的item的位置
    private int lastVisibleItemPosition;
    //底部加载更多的view
    private LoadMoreFooterView loadMoreFooterView;
    //加载更多回调接口
    private LoadModeDataListener loadModeDataListener;
    //状态控制位
    private boolean isLoadMoreEnabled = false;
    private int loadMoreState = -1;

    public interface LoadModeDataListener {

        void onLoadMore();

        void onRetry();
    }

    public void setLoadModeDataListener(LoadModeDataListener loadModeDataListener) {
        this.loadModeDataListener = loadModeDataListener;
    }

    /**
     * 开启或者关闭上拉加载
     *
     * @param loadMoreEnabled
     */
    public void setLoadMoreEnabled(boolean loadMoreEnabled) {
        isLoadMoreEnabled = loadMoreEnabled;
    }

    /**
     * 当前上拉记载的状态
     *
     * @return
     */
    public int getLoadMoreState() {
        return loadMoreState;
    }

    public LRecyclerView(Context context) {
        super(context);
        initView();
    }

    public LRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public LRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public void initView() {
        //添加滑动监听，滑动到底部加载更多
        this.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!isLoadMoreEnabled) {
                    Logger.d("不支持上拉加载,或当前处于关闭状态中。。。");
                    return;
                }
                RecyclerView.LayoutManager layoutManager = getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                if (loadMoreState != LoadMoreFooterView.LOAD_MORE_STATE_LOADING) {
                    //可能添加header和footer
                    if (visibleItemCount >= 2 && newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition == totalItemCount - 1) {
                        if (loadModeDataListener != null) {
                            changeLoadMoreState(LoadMoreFooterView.LOAD_MORE_STATE_LOADING);
                            loadModeDataListener.onLoadMore();
                        }
                    }
                } else {
                    Logger.d("正在加载...");
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!isLoadMoreEnabled) {
                    Logger.d("不支持上拉加载,或当前处于关闭状态中。。。");
                    return;
                }
                RecyclerView.LayoutManager layoutManager = getLayoutManager();
                if (layoutManagerType == -1) {
                    if (layoutManager instanceof LinearLayoutManager) {
                        layoutManagerType = MConstants.RECYCLER_LINEAR;
                    } else if (layoutManager instanceof GridLayoutManager) {
                        layoutManagerType = MConstants.RECYCLER_GRID;
                    } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                        layoutManagerType = MConstants.RECYCLER_STAGGERED_GRID;
                    } else {
                        throw new RuntimeException(
                                "Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager");
                    }
                }

                switch (layoutManagerType) {
                    case MConstants.RECYCLER_LINEAR:
                        lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                        break;
                    case MConstants.RECYCLER_GRID:
                        lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
                        break;
                    case MConstants.RECYCLER_STAGGERED_GRID:
                        StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                        if (lastPositions == null) {
                            lastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                        }
                        staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions);
                        lastVisibleItemPosition = findMax(lastPositions);
                        break;
                }
            }
        });
    }

    /**
     * 修改底部加载更多vie的状态
     *
     * @param loadMoreState
     */
    public void changeLoadMoreState(int loadMoreState) {
        this.loadMoreState = loadMoreState;
        if (loadMoreFooterView != null && getAdapter() != null) {
            switch (loadMoreState) {
                case LoadMoreFooterView.LOAD_MORE_STATE_INITIAL:
                case LoadMoreFooterView.LOAD_MORE_STATE_COMPLETE:
                    getAdapter().notifyItemRemoved(((SuperAdapter) getAdapter()).hasHeaderView() ? getAdapter().getItemCount() + 1 : getAdapter().getItemCount());
                    setLoadMoreEnabled(true);
                    break;
                case LoadMoreFooterView.LOAD_MORE_STATE_LOADING:
                case LoadMoreFooterView.LOAD_MORE_STATE_ERROR:
                    getAdapter().notifyItemInserted(getAdapter().getItemCount());
                    //加载中、加载失败，关闭上拉加载功能
                    setLoadMoreEnabled(false);
                    break;
                case LoadMoreFooterView.LOAD_MORE_STATE_NODATA:
                    getAdapter().notifyItemInserted(getAdapter().getItemCount());
                    //暂无数据，关闭上拉加载功能
                    setLoadMoreEnabled(false);
                    break;
                default:
                    break;
            }
            loadMoreFooterView.chanageLoadMoreState(loadMoreState);
        }
    }

    /**
     * 绑定适配器，是否支持上拉加载
     *
     * @param superAdapter
     * @param isLoadMoreEnabled
     */
    public void setAdapter(SuperAdapter superAdapter, boolean isLoadMoreEnabled) {
        if (isLoadMoreEnabled) {
            this.isLoadMoreEnabled = isLoadMoreEnabled;
            loadMoreFooterView = new LoadMoreFooterView(getContext());
            loadMoreFooterView.setOnRetryListener(new LoadMoreFooterView.OnRetryListener() {
                @Override
                public void onRetryLoadMore() {
                    loadModeDataListener.onRetry();
                }
            });
            superAdapter.addFooterView(loadMoreFooterView);
        }
        this.setAdapter(superAdapter);
    }

    public void switchType(int showType) {
        if (getAdapter() == null) {
            Logger.d("还没有设置adapter。。。");
            return;
        }
//        if ((SuperAdapter.class).isAssignableFrom(getAdapter().getClass())) {
//            JLog.d("除SuperAdapter,不支持其他的适配器！");
//            return;
//        }
        if (showType == MConstants.RECYCLER_LINEAR) {
            this.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
        } else {
            final GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return ((SuperAdapter) getAdapter()).isHeaderView(position) || ((SuperAdapter) getAdapter()).isFooterView(position) ? gridLayoutManager.getSpanCount() : 1;
                }
            });
            this.setLayoutManager(gridLayoutManager);
        }
    }

    /**
     * findMax
     *
     * @param lastPositions
     * @return
     */
    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

}