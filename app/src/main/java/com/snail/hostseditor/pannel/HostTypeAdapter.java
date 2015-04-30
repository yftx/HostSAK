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




/*

public class HostTypeAdapter extends RecyclerView.Adapter<HostTypeAdapter.ViewHolder> {
    public interface HostTypeClickedListener{
        public void onHostTypeClick();
    }




    List<HostType> datas;
    LayoutInflater inflater;
    OnClickItemListener onClickItemListener;

    HostTypeAdapter(List<HostType> datas, LayoutInflater inflater, OnClickItemListener onClickItemListener) {
        this.datas = datas;
        this.inflater = inflater;
        this.onClickItemListener = onClickItemListener;
    }

    interface OnClickItemListener {
        public void onClickItem(HostType hostType);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.host_type)
        public TextView hostType;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.inject(this,v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickItemListener.onClickItem(datas.get(getPosition()));
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.host_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.hostType.setText(datas.get(position).name);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}

*/

