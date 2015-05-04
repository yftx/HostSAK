package com.snail.hostseditor.pannel;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.hannesdorfmann.mosby.dagger1.Dagger1MosbyActivity;

/**
 * Created by yftx on 2/1/15.
 */
public class PannelActivity extends Dagger1MosbyActivity {
    public static final String PANNEL_FRAGMENT_TAG = "pannel_fragment_tag";
    Fragment mPannelFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPannelFragment = getSupportFragmentManager().findFragmentByTag(PANNEL_FRAGMENT_TAG);
        if (mPannelFragment == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(android.R.id.content, new PannelFragment(), PANNEL_FRAGMENT_TAG)
                    .commit();
        }
    }
}
