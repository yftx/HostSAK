package com.snail.hostseditor.pannel;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hannesdorfmann.mosby.dagger1.viewstate.lce.Dagger1MvpLceViewStateFragment;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingFragmentLceViewState;
import com.snail.hostseditor.R;
import com.snail.hostseditor.model.HostType;

import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;

/**
 * Created by yftx on 4/21/15.
 */
public class PannelFragment
        extends Dagger1MvpLceViewStateFragment<SwipeRefreshLayout, List<HostType>, PannelView, PannelPresenter>
        implements HostTypeAdapter.HostTypeClickedListener, PannelView, SwipeRefreshLayout.OnRefreshListener {
    @InjectView(R.id.list)
    RecyclerView mList;

    HostTypeAdapter mAdapter;

    @Inject
    PannelPresenter mPannelPresenter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        contentView.setOnRefreshListener(this);
        mAdapter = new HostTypeAdapter(getActivity(), this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mList.setLayoutManager(mLayoutManager);
        mList.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.pannel_fragment;
    }

    @Override
    public LceViewState<List<HostType>, PannelView> createViewState() {
        return new RetainingFragmentLceViewState<List<HostType>, PannelView>(this);
    }

    @Override
    public List<HostType> getData() {
        return mAdapter.getItems();
    }


    @Override
    protected PannelPresenter createPresenter() {
        return mPannelPresenter;
    }

    @Override
    public void setData(List<HostType> types) {
        mAdapter.setItems(types);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return null;
    }

    @Override
    public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        contentView.setRefreshing(false);
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        super.showLoading(pullToRefresh);
        if (pullToRefresh && !contentView.isRefreshing()) {
            // Workaround for measure bug: https://code.google.com/p/android/issues/detail?id=77712
            contentView.post(new Runnable() {
                @Override
                public void run() {
                    contentView.setRefreshing(true);
                }
            });
        }
    }


    @Override
    public void loadData(boolean b) {
        presenter.loadData(b);
    }

    @Override
    public void onHostTypeClick(HostTypeAdapterHolders.HostTypeItemViewHolder vh, HostType hostType) {

    }

    @Override
    protected void injectDependencies() {
        getObjectGraph().inject(this);
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }
}
