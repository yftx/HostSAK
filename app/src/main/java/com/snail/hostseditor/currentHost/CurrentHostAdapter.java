package com.snail.hostseditor.currentHost;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.hannesdorfmann.annotatedadapter.annotation.Field;
import com.hannesdorfmann.annotatedadapter.annotation.ViewType;
import com.hannesdorfmann.annotatedadapter.support.recyclerview.SupportAnnotatedAdapter;
import com.snail.hostseditor.R;
import com.snail.hostseditor.core.util.ThreadPreconditions;
import com.snail.hostseditor.model.Host;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

public class CurrentHostAdapter extends SupportAnnotatedAdapter implements CurrentHostAdapterBinder {

    @ViewType(
            layout = R.layout.current_host_item,
            fields = {
                    @Field(
                            id = R.id.item_container,
                            name = "container",
                            type = CardView.class
                    ),

                    @Field(
                            id = R.id.name,
                            name = "name",
                            type = TextView.class
                    ),
                    @Field(
                            id = R.id.ip,
                            name = "ip",
                            type = TextView.class
                    )
            }
    )
    public static final int TYPE = 0;

    private List<Host> mHosts = Collections.emptyList();
    private int ITEM_OUTER_SIZE = 0;

    @Inject
    protected CurrentHostAdapter(Context context) {
        super(context);
        ITEM_OUTER_SIZE = (int) context.getResources().getDimension(R.dimen.outer_margin);
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
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mHosts.size();
    }

    public List<Host> getItems() {
        return mHosts;
    }


    @Override
    public void bindViewHolder(CurrentHostAdapterHolders.TYPEViewHolder vh, int position) {
        final Host host = mHosts.get(position);
        vh.ip.setText(host.getIp());
        vh.name.setText(host.getHostName());

        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) vh.container.getLayoutParams();
        params.setMargins(ITEM_OUTER_SIZE, ITEM_OUTER_SIZE, ITEM_OUTER_SIZE, position == getItemCount() - 1 ? ITEM_OUTER_SIZE : 0);
        vh.container.setLayoutParams(params);
    }
}
