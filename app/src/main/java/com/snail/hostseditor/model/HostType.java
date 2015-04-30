package com.snail.hostseditor.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.hannesdorfmann.parcelableplease.annotation.ParcelablePlease;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by yftx on 2/2/15.
 */
@ParcelablePlease
public class HostType implements Parcelable{
    public static List<HostType> parse(JSONObject json) {
        List<HostType> hostTypes = new ArrayList<HostType>();
        try {
            JSONArray array = json.getJSONArray("results");
            for (int i = 0; i < array.length(); i++) {
                String name = array.getJSONObject(i).getString("name");
                int index = array.getJSONObject(i).getInt("index");
                hostTypes.add(new HostType(name,index));
            }
        } catch (JSONException e) {
            Timber.e(e.getMessage());
        }

        return hostTypes;
    }

    public String name;
    public int index;

    public HostType(String name, int index) {
        this.name = name;
        this.index = index;
    }

    @Override
    public String toString() {
        return "HostType{" +
                "name='" + name + '\'' +
                ", index=" + index +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        HostTypeParcelablePlease.writeToParcel(this, dest, flags);
    }
}

