package com.snail.hostseditor.currentHost;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingFragmentLceViewState;
import com.snail.hostseditor.BaseFragment;
import com.snail.hostseditor.R;
import com.snail.hostseditor.core.Host;
import com.snail.hostseditor.ui.list.ListHostsAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;

/**
 * Created by yftx on 5/4/15.
 */
public class CurrentHostFragment
        extends BaseFragment<ListView, List<Host>, CurrentHostView, CurrentHostPresenter>
        implements CurrentHostView {

    @Inject
    CurrentHostPresenter mPresenter;

    @Inject
    ListHostsAdapter mAdapter;

    @InjectView(R.id.contentView)
    ListView mListView;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public LceViewState<List<Host>, CurrentHostView> createViewState() {
        return new RetainingFragmentLceViewState<List<Host>, CurrentHostView>(this);
    }

    @Override
    public List<Host> getData() {
        return mAdapter.getItems();
    }

    @Override
    protected CurrentHostPresenter createPresenter() {
        return mPresenter;
    }

    @Override
    public void setData(List<Host> data) {
        mAdapter.setData(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadData(pullToRefresh);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.current_hosts_fragment;
    }
}
