package com.snail.hostseditor.ui;

import android.os.Bundle;

import com.hannesdorfmann.mosby.dagger1.Dagger1MosbyActivity;
import com.snail.hostseditor.App;
import com.snail.hostseditor.ui.extend.NetEngine;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import dagger.ObjectGraph;

/**
 * All activities should extend this for dependency injection.
 */
public abstract class BaseActivity extends Dagger1MosbyActivity {

    @Inject
    protected App mApp;

    @Inject
    protected NetEngine engine;

    @Inject
    protected Bus mBus;

    protected ObjectGraph objectGraph;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        objectGraph = getObjectGraph();
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
