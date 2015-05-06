package com.snail.hostseditor.pannel;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.hannesdorfmann.annotatedadapter.annotation.Field;
import com.hannesdorfmann.annotatedadapter.annotation.ViewType;
import com.hannesdorfmann.annotatedadapter.support.recyclerview.SupportAnnotatedAdapter;
import com.snail.hostseditor.R;
import com.snail.hostseditor.model.HostType;

import java.util.List;

/**
 * Created by yftx on 2/4/15.
 */

public class HostTypeAdapter extends SupportAnnotatedAdapter implements HostTypeAdapterBinder {
    public interface HostTypeClickedListener {
        void onHostTypeClick(HostTypeAdapterHolders.HostTypeItemViewHolder vh, HostType hostType);
    }

    protected List<HostType> items;

    @ViewType(layout = R.layout.host_list_item,
            fields = {
                    @Field(
                            id = R.id.host_type,
                            name = "hostType",
                            type = TextView.class)
            }
    )
    public final int HostTypeItem = 0;
    private HostTypeClickedListener mClickListener;

    public HostTypeAdapter(Context context, HostTypeClickedListener listener) {
        super(context);
        mClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return HostTypeItem;
    }

    public List<HostType> getItems() {
        return items;
    }

    public void setItems(List<HostType> items) {
        this.items = items;
    }


    @Override
    public void bindViewHolder(final HostTypeAdapterHolders.HostTypeItemViewHolder vh, int position) {
        final HostType type = items.get(position);
        vh.hostType.setText(type.name);
        vh.hostType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onHostTypeClick(vh, type);
            }
        });
    }
}


