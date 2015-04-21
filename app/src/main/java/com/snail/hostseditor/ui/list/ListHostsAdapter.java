package com.snail.hostseditor.ui.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.snail.hostseditor.R;
import com.snail.hostseditor.core.Host;
import com.snail.hostseditor.core.util.ThreadPreconditions;
import com.snail.hostseditor.ui.widget.CheckableRelativeLayout;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class ListHostsAdapter extends BaseAdapter implements Filterable {

    @Inject
    ListHostsSearchFilter mSearchFilter;

    private List<Host> mHosts = Collections.emptyList();
    private Context mAppContext;


    public void init(Context appContext) {
        mAppContext = appContext;
    }

    public void updateHosts(List<Host> hosts) {
        ThreadPreconditions.checkOnMainThread();
        if (hosts == null) {
            mHosts = Collections.emptyList();
        } else {
            mHosts = hosts;
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mHosts.size();
    }

    @Override
    public Host getItem(int position) {
        return mHosts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mAppContext).inflate(R.layout.current_host_item, parent, false);
        }

        Host host = getItem(position);
        ((CheckableRelativeLayout) convertView).drawItem(host);
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return mSearchFilter;
    }
}
