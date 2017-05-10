package com.chx.pullrefresh.ultra.header;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.chx.pullrefresh.R;

/**
 * Created by srain on 11/7/14.
 */
public class GapV extends LinearLayout {

    private View gapView;
    private float width = -1;

    public GapV(Context context) {
        super(context);
        init();
    }

    public GapV(Context context, float width) {
        super(context);
        this.width = width;
    }

    public GapV(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GapV(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        gapView = LayoutInflater.from(getContext()).inflate(R.layout.layout_gap_v, this);
        if (width != -1) {
            gapView.setLayoutParams(new LayoutParams((int) width, LayoutParams.MATCH_PARENT));
        }

    }
}
