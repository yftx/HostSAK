package com.snail.hostseditor.currentHost;

import android.content.Context;

import com.hannesdorfmann.annotatedadapter.AbsListViewAnnotatedAdapter;
import com.hannesdorfmann.annotatedadapter.annotation.Field;
import com.hannesdorfmann.annotatedadapter.annotation.ViewType;
import com.snail.hostseditor.R;
import com.snail.hostseditor.model.Host;
import com.snail.hostseditor.core.util.ThreadPreconditions;
import com.snail.hostseditor.widget.CheckableRelativeLayout;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class CurrentHostAdapter extends AbsListViewAnnotatedAdapter implements CurrentHostAdapterBinder {

    @ViewType(
            layout = R.layout.current_host_item,
            fields = {
                    @Field(
                            id = R.id.current_host_container,
                            name = "item",
                            type = CheckableRelativeLayout.class
                    )
            }
    )
    public static final int TYPE = 0;

    private List<Host> mHosts = Collections.emptyList();

    @Inject
    protected CurrentHostAdapter(Context context) {
        super(context);
    }

    public void setData(List<Host> hosts) {
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

    public List<Host> getItems() {
        return mHosts;
    }

    @Override
    public void bindViewHolder(CurrentHostAdapterHolders.TYPEViewHolder vh, int position) {
        Host host = getItem(position);
        vh.item.drawItem(host);
    }
}
