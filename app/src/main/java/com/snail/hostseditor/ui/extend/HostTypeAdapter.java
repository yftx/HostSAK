package com.snail.hostseditor.ui.extend;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.snail.hostseditor.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by yftx on 2/4/15.
 */
public class HostTypeAdapter extends RecyclerView.Adapter<HostTypeAdapter.ViewHolder> {
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


