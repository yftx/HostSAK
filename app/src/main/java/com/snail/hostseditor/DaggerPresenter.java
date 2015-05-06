package com.snail.hostseditor;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;
import com.squareup.otto.Bus;

import javax.inject.Inject;

/**
 * Created by yftx on 5/4/15.
 */
public abstract class DaggerPresenter<V extends MvpView> extends MvpBasePresenter<V> {
    @Inject
    protected Bus mBus;

    @Inject
    protected App mApp;

    public <T> T getDIObject(Class<T> type) {
        return mApp.getObjectGraph().get(type);
    }


    @Override
    public void attachView(V view) {
        super.attachView(view);
        mBus.register(this);
    }


    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        mBus.unregister(this);
    }

}
