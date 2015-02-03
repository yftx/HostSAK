package com.nilhcem.hostseditor.ui.extend;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yftx on 2/2/15.
 */
public class HostType {
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
            e.printStackTrace();
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
}

