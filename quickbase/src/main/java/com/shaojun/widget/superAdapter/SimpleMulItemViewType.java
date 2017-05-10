package com.shaojun.widget.superAdapter;

/**
 * <p>Convenient class for RecyclerView.Adapter.</p>
 */
public abstract class SimpleMulItemViewType<T> implements IMulItemViewType<T> {

    @Override
    public int getViewTypeCount() {
        return 1;
    }

}
