package com.swift.nhat.applottery.Utils;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nhat on 4/25/17.
 */

public class DataExtral implements Parcelable {


    Map<String, ArrayList<String>> data_map = new HashMap<>();

    public DataExtral(Map<String, ArrayList<String>> data_map) {
        this.data_map = data_map;

    }

    public Map<String, ArrayList<String>> getData_map() {
        return data_map;
    }

    public void setData_map(Map<String, ArrayList<String>> data_map) {
        this.data_map = data_map;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        final int N = data_map.size();
        dest.writeInt(N);
        if (N > 0) {
            for (Map.Entry<String, ArrayList<String>> entry : data_map.entrySet()) {
                dest.writeString(entry.getKey());
                dest.writeStringList(entry.getValue());
            }
        }
    }
    public static final Creator<DataExtral> CREATOR = new Creator<DataExtral>() {
        public DataExtral createFromParcel(Parcel source) {
            return new DataExtral(source);
        }
        public DataExtral[] newArray(int size) {
            return new DataExtral[size];
        }
    };

    private DataExtral(Parcel source) {
        final int N = source.readInt();
        for (int i=0; i<N; i++) {
            String key = source.readString();
            ArrayList<String> list = new ArrayList<>();
            source.readStringList(list);
            data_map.put(key, list);
        }
    }
}
