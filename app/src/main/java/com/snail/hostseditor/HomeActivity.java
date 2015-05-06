package com.snail.hostseditor;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.hannesdorfmann.mosby.dagger1.Dagger1MosbyActivity;
import com.snail.hostseditor.currentHost.CurrentHostFragment;
import com.snail.hostseditor.event.ShowCurrentHostEvent;
import com.snail.hostseditor.pannel.PanelFragment;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

/**
 * Created by yftx on 2/1/15.
 */
public class HomeActivity extends Dagger1MosbyActivity {
    public static final String PANEL_FRAGMENT_TAG = "panel_fragment_tag";
    public static final String CURRENT_HOST_FRAGMENT_TAG = "current_host_fragment_tag";

    @Inject
    Bus mBus;

    FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_dummy_layout);
        FragmentManager.enableDebugLogging(true);
        mBus.register(this);
        mFragmentManager = getSupportFragmentManager();

        if (mFragmentManager.findFragmentByTag(PANEL_FRAGMENT_TAG) == null) {
            mFragmentManager.beginTransaction()
                    .replace(R.id.dummy_fragment_container, new PanelFragment(), PANEL_FRAGMENT_TAG)
                    .commit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBus.unregister(this);
    }


    @Subscribe
    public void onReceiveShowCurrentHost(ShowCurrentHostEvent event) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.replace(R.id.dummy_fragment_container, new CurrentHostFragment(), CURRENT_HOST_FRAGMENT_TAG);
        ft.addToBackStack(null);
        ft.commit();
    }
}
