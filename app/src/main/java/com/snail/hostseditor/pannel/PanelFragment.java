package com.snail.hostseditor.pannel;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingFragmentLceViewState;
import com.snail.hostseditor.BaseFragment;
import com.snail.hostseditor.R;
import com.snail.hostseditor.model.HostType;

import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by yftx on 4/21/15.
 */
public class PanelFragment
        extends BaseFragment<SwipeRefreshLayout, List<HostType>, PanelView, PanelPresenter>
        implements HostTypeAdapter.HostTypeClickedListener, PanelView, SwipeRefreshLayout.OnRefreshListener {
    @InjectView(R.id.list)
    RecyclerView mList;

    HostTypeAdapter mAdapter;

    ProgressDialog progressDialog;

    @Inject
    PanelPresenter mPanelPresenter;

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
    public LceViewState<List<HostType>, PanelView> createViewState() {
        return new RetainingFragmentLceViewState<List<HostType>, PanelView>(this);
    }

    @Override
    public List<HostType> getData() {
        return mAdapter.getItems();
    }


    @Override
    protected PanelPresenter createPresenter() {
        return mPanelPresenter;
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
    public void onRefresh() {
        loadData(true);
    }


    @Override
    public void onHostTypeClick(HostTypeAdapterHolders.HostTypeItemViewHolder vh, HostType hostType) {
        presenter.alertReplaceHost(hostType);
    }


    @Override
    public void showAlertReplaceHostDialog(final HostType hostType) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                presenter.replaceHost(hostType);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(R.string.cancel, null);
        builder.setTitle(R.string.warn_title);
        builder.setMessage(R.string.warn_content);
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    public void showReplacingHostDialog() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.replace_content));
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    @Override
    public void hideReplacingHostDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    @OnClick(R.id.show_current_host)
    public void onClickShowCurrentHost() {
        presenter.showCurrentHost();
    }
}
