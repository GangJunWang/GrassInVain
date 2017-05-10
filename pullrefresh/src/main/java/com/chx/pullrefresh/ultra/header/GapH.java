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
public class GapH extends LinearLayout {
    private View gapView;
    private float height = -1;

    public GapH(Context context) {
        super(context);
        init();
    }

    public GapH(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GapH(Context context, float height) {
        super(context);
        this.height = height;
    }

    public GapH(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        gapView = LayoutInflater.from(getContext()).inflate(R.layout.layout_gap_h, this);
        if (height != -1) {
            gapView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, (int) height));
        }
    }
}
