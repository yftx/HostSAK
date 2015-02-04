package com.snail.hostseditor.ui.list;

import android.text.TextUtils;
import android.widget.Filter;

import com.snail.hostseditor.core.Host;
import com.snail.hostseditor.core.HostsManager;
import com.snail.hostseditor.event.RefreshHostsEvent;
import com.squareup.otto.Bus;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

class ListHostsSearchFilter extends Filter {

    @Inject Bus mBus;
    @Inject HostsManager mHostsManager;

    @SuppressWarnings("unchecked")
    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        if (!TextUtils.isEmpty(constraint)) {
            Timber.d("Publishing result for: %s", constraint);
        }
        mBus.post(new RefreshHostsEvent((List<Host>) results.values));
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        if (!TextUtils.isEmpty(constraint)) {
            Timber.d("Perform filtering for: %s", constraint);
        }
        FilterResults results = new FilterResults();
        results.values = mHostsManager.filterHosts(constraint);
        return results;
    }
}
