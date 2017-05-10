package bluetooth.inuker.com.grassinvain.base.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chx.pullrefresh.ultra.PtrFrameLayout;
import com.chx.pullrefresh.ultra.PtrHTFrameLayout;

import bluetooth.inuker.com.grassinvain.R;

public abstract class BaseRefreshFragment extends BasePageFragment {

    protected PtrHTFrameLayout ptrHTFrameLayout;
    protected RecyclerView recyclerView;

    @Override
    protected void initWidget(View rootView) {
        ptrHTFrameLayout = (PtrHTFrameLayout) rootView.findViewById(R.id.phf_refresh);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        ptrHTFrameLayout.setRecyclerView(recyclerView);
        initRefreshWidget(rootView);
    }

    public abstract void initRefreshWidget(View rootView);

    public void setRefreshParams(int top, int bottom, int left, int right){
        PtrFrameLayout.LayoutParams layoutParams = (PtrFrameLayout.LayoutParams) recyclerView.getLayoutParams();
        layoutParams.leftMargin = left;
        layoutParams.rightMargin = right;
        layoutParams.topMargin = top;
        layoutParams.bottomMargin = bottom;
        recyclerView.setLayoutParams(layoutParams);
    }

}