package com.snail.hostseditor.ui.extend;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.snail.hostseditor.App;
import com.snail.hostseditor.R;
import com.snail.hostseditor.core.Host;
import com.snail.hostseditor.event.TaskCompletedEvent;
import com.snail.hostseditor.task.GenericTaskAsync;
import com.snail.hostseditor.task.ReplaceHostAsync;
import com.snail.hostseditor.ui.BaseActivity;
import com.snail.hostseditor.ui.list.ListHostsActivity;
import com.squareup.otto.Subscribe;

import org.json.JSONObject;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Created by yftx on 2/1/15.
 */
public class MainActivity extends BaseActivity {
    @InjectView(R.id.list)
    ListView mList;
    @InjectView(android.R.id.empty)
    View mEmptyView;

    List<HostType> datas;
    HostTypeAdapter mAdapter;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.main_activity);
        ButterKnife.inject(this);
        showLoading();
        requestData();
    }

    private void requestData() {
        engine.getHostList(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                datas = HostType.parse(jsonObject);
                refreshData();
            }
        });
    }

    private void refreshData() {
        mAdapter = new HostTypeAdapter(datas, getLayoutInflater());
        mList.setAdapter(mAdapter);
        showContent();
    }

    @OnItemClick(R.id.list)
    public void changeHost(int postion) {
        if (mAdapter == null) return;
        final HostType hostType = mAdapter.getItem(postion);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                replaceHost(hostType);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        builder.setTitle(R.string.warn_title);
        builder.setMessage(R.string.warn_content);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void replaceHost(HostType hostType) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.replace_content));
        progressDialog.show();
        progressDialog.setCancelable(false);
        engine.getHost(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                List<Host> hosts = Host.fromJson(jsonObject);
                replaceHost(hosts.toArray(new Host[hosts.size()]));
            }
        }, hostType.index);
    }

    public void replaceHost(Host[] hosts) {
        GenericTaskAsync task = mApp.get(ReplaceHostAsync.class);
        task.init(getApplicationContext(), false);
        task.execute(hosts);
    }

    @Subscribe
    public void onTaskFinished(TaskCompletedEvent task) {
        if (progressDialog != null)
            progressDialog.dismiss();
        showCurrenHost();
    }

    private void showContent() {
        mList.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.GONE);
    }

    private void showLoading() {
        mList.setVisibility(View.GONE);
        mEmptyView.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.show_current_host)
    public void showCurrenHost() {
        startActivity(new Intent(this, ListHostsActivity.class));
    }
}
