package com.snail.hostseditor.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;

import com.snail.hostseditor.App;
import com.squareup.otto.Bus;

import javax.inject.Inject;

/**
 * All fragments should extend this for dependency injection.
 */
public class BaseFragment extends Fragment {

    @Inject
    protected Bus mBus;

    protected ActionBarActivity mActivity;
    @Inject
    protected App mApp;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (ActionBarActivity) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        App.get(mActivity).inject(this);
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
}
