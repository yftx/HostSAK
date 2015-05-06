package com.snail.hostseditor.pannel;

import com.snail.hostseditor.DaggerPresenter;
import com.snail.hostseditor.event.LoadHostTypeEvent;
import com.snail.hostseditor.event.LoadHostsEvent;
import com.snail.hostseditor.event.ShowCurrentHostEvent;
import com.snail.hostseditor.event.TaskCompletedEvent;
import com.snail.hostseditor.model.HostType;
import com.snail.hostseditor.task.GenericTaskAsync;
import com.snail.hostseditor.task.ReplaceHostAsync;
import com.snail.hostseditor.ui.extend.NetEngine;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by yftx on 4/21/15.
 */
public class PanelPresenter extends DaggerPresenter<PanelView> {

    public List<HostType> mHostTypes;

    @Inject
    NetEngine mEngine;

    @Inject
    public PanelPresenter() {
        mHostTypes = new ArrayList<HostType>();
    }

    public void loadData(boolean showLoadingIndicator) {
        if (isViewAttached()) {
            getView().showLoading(showLoadingIndicator);
            mEngine.getHostTypes();
        }
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
        GenericTaskAsync task = getDIObject(ReplaceHostAsync.class);
        task.init(false);
        task.execute(event.getHosts());
    }

    @Subscribe
    public void onTaskFinished(TaskCompletedEvent task) {
        if (isViewAttached())
            getView().hideReplacingHostDialog();
        showCurrentHost();
    }

    /**
     * 打开当前的Host界面
     */
    public void showCurrentHost() {
        mBus.post(getDIObject(ShowCurrentHostEvent.class));
    }
}
