package com.snail.hostseditor.ui.extend;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.snail.hostseditor.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by yftx on 2/4/15.
 */
public class HostTypeAdapter extends BaseAdapter {

    List<HostType> datas;
    LayoutInflater inflater;

    HostTypeAdapter(List<HostType> datas, LayoutInflater inflater) {
        this.datas = datas;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public HostType getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.host_list_item, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        holder.hostType.setText(getItem(position).name);
        return view;
    }


    static class ViewHolder {
        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }

        @InjectView(R.id.host_type)
        TextView hostType;
    }
}


