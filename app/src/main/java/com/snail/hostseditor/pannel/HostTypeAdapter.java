package com.snail.hostseditor.pannel;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
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
    private int ITEM_OUTER_SIZE = 0;
    protected List<HostType> items;

    @ViewType(layout = R.layout.host_list_item,
            fields = {
                    @Field(
                            id = R.id.item_container,
                            name = "container",
                            type = CardView.class
                    ),
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
        ITEM_OUTER_SIZE = (int) context.getResources().getDimension(R.dimen.outer_margin);
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

        //当界面在最后一个的时候设置item的margin，保证美观。
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) vh.container.getLayoutParams();
        params.setMargins(ITEM_OUTER_SIZE, ITEM_OUTER_SIZE, ITEM_OUTER_SIZE, position == getItemCount() - 1 ? ITEM_OUTER_SIZE : 0);
        vh.container.setLayoutParams(params);
    }
}


