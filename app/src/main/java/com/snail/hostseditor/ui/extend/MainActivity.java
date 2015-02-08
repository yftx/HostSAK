package com.snail.hostseditor.ui.extend;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.snail.hostseditor.R;
import com.snail.hostseditor.event.LoadHostTypeEvent;
import com.snail.hostseditor.event.LoadHostsEvent;
import com.snail.hostseditor.event.TaskCompletedEvent;
import com.snail.hostseditor.task.GenericTaskAsync;
import com.snail.hostseditor.task.ReplaceHostAsync;
import com.snail.hostseditor.ui.BaseActivity;
import com.snail.hostseditor.ui.list.ListHostsActivity;
import com.squareup.otto.Subscribe;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by yftx on 2/1/15.
 */
public class MainActivity extends BaseActivity {
    @InjectView(R.id.list)
    RecyclerView mList;
    @InjectView(android.R.id.empty)
    View mEmptyView;

    private RecyclerView.LayoutManager mLayoutManager;

    List<HostType> mHostTypes;
    HostTypeAdapter mAdapter;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.main_activity);
        ButterKnife.inject(this);
        showLoading();
        engine.getHostList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.show_current_host:
                showCurrentHost();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Subscribe
    public void refreshData(LoadHostTypeEvent event) {
        mHostTypes = event.hostTypes;
        mAdapter = new HostTypeAdapter(mHostTypes, getLayoutInflater(), new HostTypeAdapter.OnClickItemListener() {
            @Override
            public void onClickItem(HostType hostType) {
                changeHost(hostType);
            }
        });
        mLayoutManager = new LinearLayoutManager(this);
        mList.setLayoutManager(mLayoutManager);
        mList.setAdapter(mAdapter);
        showContent();
    }


    public void changeHost(HostType hostType) {
        if (mAdapter == null) return;
        showChangeHostDialog(hostType);
    }

    private void showChangeHostDialog(final HostType hostType) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                replaceHost(hostType);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(R.string.cancel, null);
        builder.setTitle(R.string.warn_title);
        builder.setMessage(R.string.warn_content);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void replaceHost(HostType hostType) {
        showProgressBar();
        engine.getHost(hostType.index);
    }

    private void showProgressBar() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.replace_content));
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    @Subscribe
    public void replaceHost(LoadHostsEvent event) {
        GenericTaskAsync task = mApp.get(ReplaceHostAsync.class);
        task.init(getApplicationContext(), false);
        task.execute(event.getHosts());
    }

    @Subscribe
    public void onTaskFinished(TaskCompletedEvent task) {
        if (progressDialog != null)
            progressDialog.dismiss();
        showCurrentHost();
    }

    private void showContent() {
        mList.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);
    }

    private void showLoading() {
        mList.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
    }

    //    @OnClick(R.id.show_current_host)
    public void showCurrentHost() {
        startActivity(new Intent(this, ListHostsActivity.class));
    }
}
