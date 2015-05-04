package com.snail.hostseditor.pannel;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.hannesdorfmann.mosby.MosbyActivity;
import com.hannesdorfmann.mosby.dagger1.Dagger1MosbyActivity;

import javax.inject.Inject;

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

    /*    @InjectView(R.id.list)
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
        setContentView(R.layout.pannel_fragment);
        showLoading();
        engine.getHostList();
    }

    @Override
    protected MvpPresenter createPresenter() {
        return null;
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
        GenericTaskAsync task = objectGraph.get(ReplaceHostAsync.class);

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
    */
}
