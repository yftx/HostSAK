package com.snail.hostseditor.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.hannesdorfmann.mosby.dagger1.viewstate.Dagger1MvpViewStateFragment;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;
import com.snail.hostseditor.App;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import dagger.ObjectGraph;

/**
 * All fragments should extend this for dependency injection.
 */
public  abstract class BaseFragment extends Dagger1MvpViewStateFragment {

    @Inject
    protected Bus mBus;

    protected ActionBarActivity mActivity;

    @Inject
    protected App mApp;

    protected ObjectGraph objectGraph;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        objectGraph = getObjectGraph();
        mActivity = (ActionBarActivity) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        mBus.register(this);
    }

    @Override
    public void onPause() {
        mBus.unregister(this);
        super.onPause();
    }

    @Override
    public void onDetach() {
        mApp = null;
        mActivity = null;
        super.onDetach();
    }

    @Override
    public ViewState createViewState() {
        return null;
    }

    @Override
    public void onNewViewStateInstance() {

    }

    @Override
    protected MvpPresenter createPresenter() {
        return null;
    }
}
