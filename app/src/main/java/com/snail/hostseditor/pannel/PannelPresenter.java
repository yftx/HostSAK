package com.snail.hostseditor.pannel;

import com.hannesdorfmann.mosby.dagger1.Injector;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.snail.hostseditor.App;
import com.snail.hostseditor.event.LoadHostTypeEvent;
import com.snail.hostseditor.event.LoadHostsEvent;
import com.snail.hostseditor.event.TaskCompletedEvent;
import com.snail.hostseditor.model.HostType;
import com.snail.hostseditor.task.GenericTaskAsync;
import com.snail.hostseditor.task.ReplaceHostAsync;
import com.snail.hostseditor.ui.extend.NetEngine;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by yftx on 4/21/15.
 */
public class PannelPresenter extends MvpBasePresenter<PannelView> {

    public List<HostType> mHostTypes;

    @Inject
    NetEngine mEngine;
    @Inject
    Bus mBus;

    @Inject
    Injector mInjector;

    @Inject
    App mApp;

    @Inject
    public PannelPresenter() {
        mHostTypes = new ArrayList<HostType>();
    }

    public void loadData(boolean showLoadingIndicator) {
        if (isViewAttached()) {
            getView().showLoading(showLoadingIndicator);
            mEngine.getHostTypes();
        }
    }


    @Override
    public void attachView(PannelView view) {
        super.attachView(view);
        mBus.register(this);
    }


    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        mBus.unregister(this);
    }

    @Subscribe
    public void onLoadHostTypeFinished(LoadHostTypeEvent event) {
        mHostTypes = event.hostTypes;
        if (isViewAttached()) {
            getView().setData(mHostTypes);
            getView().showContent();
        }
    }

    /**
     * 改变当前的host
     */
    public void alertReplaceHost(HostType hostType) {
        if (hostType == null) return;
        if (isViewAttached())
            getView().showAlertReplaceHostDialog(hostType);
    }

    public void replaceHost(HostType hostType) {
        if (isViewAttached())
            getView().showReplacingHostDialog();
        mEngine.getHost(hostType.index);


    }

    @Subscribe
    public void replaceHost(LoadHostsEvent event) {
        GenericTaskAsync task = mInjector.getObjectGraph().get(ReplaceHostAsync.class);
        task.init(false);
        task.execute(event.getHosts());
    }

    @Subscribe
    public void onTaskFinished(TaskCompletedEvent task) {
        if (isViewAttached())
            getView().hideReplacingHostDialog();
            getView().showCurrentHost();

    }
}
