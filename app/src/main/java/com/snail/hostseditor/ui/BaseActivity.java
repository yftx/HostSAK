package com.snail.hostseditor.ui;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.snail.hostseditor.App;
import com.snail.hostseditor.ui.extend.NetEngine;
import com.squareup.otto.Bus;

import javax.inject.Inject;

/**
 * All activities should extend this for dependency injection.
 */
public abstract class BaseActivity extends SherlockFragmentActivity {

    @Inject
    protected NetEngine engine;

    @Inject
    protected Bus mBus;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        App.get(this).inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBus.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mBus.unregister(this);
    }
}
