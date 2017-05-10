package com.chx.pullrefresh.ultra.loadmore;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chx.pullrefresh.R;

public class LoadMoreFooterView extends LinearLayout implements View.OnClickListener {
    private ProgressBar loadmoreProgressBar;
    private TextView loadmoreState;

    public static final int LOAD_MORE_STATE_INITIAL = 0;
    public static final int LOAD_MORE_STATE_LOADING = 1;
    public static final int LOAD_MORE_STATE_COMPLETE = 2;
    public static final int LOAD_MORE_STATE_ERROR = 3;
    public static final int LOAD_MORE_STATE_NODATA = 4;
    public int state = -1;


    private OnRetryListener listener;

    public interface OnRetryListener {
        void onRetryLoadMore();
    }

    public void setOnRetryListener(OnRetryListener listener) {
        this.listener = listener;
    }

    public LoadMoreFooterView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    public LoadMoreFooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LoadMoreFooterView(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_pull_up_load_more, this);
        loadmoreState = (TextView) findViewById(R.id.loadmore_state);
        loadmoreProgressBar = (ProgressBar) findViewById(R.id.loadmore_loading);
        this.setOnClickListener(this);
        this.setVisibility(View.GONE);
    }

    public void chanageLoadMoreState(int loadMoreState) {
        this.state = loadMoreState;
        switch (loadMoreState) {
            case LOAD_MORE_STATE_INITIAL:
                this.setVisibility(View.GONE);
                loadmoreProgressBar.setVisibility(View.VISIBLE);
                loadmoreState.setVisibility(View.GONE);
                break;
            case LOAD_MORE_STATE_LOADING:
                this.setVisibility(View.VISIBLE);
                loadmoreProgressBar.setVisibility(View.VISIBLE);
                loadmoreState.setVisibility(View.GONE);
                break;
            case LOAD_MORE_STATE_COMPLETE:
                this.setVisibility(View.GONE);
                loadmoreProgressBar.setVisibility(View.GONE);
                loadmoreState.setVisibility(View.VISIBLE);
                break;
            case LOAD_MORE_STATE_ERROR:
                this.setVisibility(View.VISIBLE);
                loadmoreProgressBar.setVisibility(View.GONE);
                loadmoreState.setVisibility(View.VISIBLE);
                loadmoreState.setText("重试");
                break;
            case LOAD_MORE_STATE_NODATA:
                this.setVisibility(View.VISIBLE);
                loadmoreProgressBar.setVisibility(View.GONE);
                loadmoreState.setVisibility(View.VISIBLE);
                loadmoreState.setText("暂无更多数据");
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if (state == LOAD_MORE_STATE_ERROR && listener != null) {
            chanageLoadMoreState(LOAD_MORE_STATE_LOADING);
            if (listener != null) {
                listener.onRetryLoadMore();
            }
        }
    }

}
