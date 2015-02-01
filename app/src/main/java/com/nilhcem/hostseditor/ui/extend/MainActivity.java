package com.nilhcem.hostseditor.ui.extend;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.nilhcem.hostseditor.R;
import com.nilhcem.hostseditor.ui.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by yftx on 2/1/15.
 */
public class MainActivity extends BaseActivity {
    @InjectView(R.id.list)
    ListView mList;
    @InjectView(android.R.id.empty)
    View mEmptyView;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.main_activity);
        ButterKnife.inject(this);

    }
}
