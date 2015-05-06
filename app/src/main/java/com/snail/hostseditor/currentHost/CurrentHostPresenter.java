package com.snail.hostseditor.currentHost;

import com.snail.hostseditor.DaggerPresenter;
import com.snail.hostseditor.event.RefreshHostsEvent;
import com.snail.hostseditor.task.ListHostsAsync;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

/**
 * Created by yftx on 5/4/15.
 */
public class CurrentHostPresenter extends DaggerPresenter<CurrentHostView> {


    @Inject
    public CurrentHostPresenter() {
    }

    public void loadData(boolean pullToRefresh) {
        if (isViewAttached()) {
            getView().showLoading(pullToRefresh);
            //每次都强制重新读取当前Host
            getDIObject(ListHostsAsync.class).execute(true);
        }
    }


    @Subscribe
    public void onHostsRefreshed(RefreshHostsEvent hosts) {
        if (isViewAttached()) {
            getView().setData(hosts.hosts);
            getView().showContent();
        }
    }
}
