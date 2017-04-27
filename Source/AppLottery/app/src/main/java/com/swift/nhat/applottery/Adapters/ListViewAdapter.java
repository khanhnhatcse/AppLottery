package com.swift.nhat.applottery.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.swift.nhat.applottery.R;

import java.util.ArrayList;

/**
 * Created by nhat on 4/25/17.
 */

public class ListViewAdapter extends ArrayAdapter<String> {
    ArrayList<String> list;
    int layout_id;
    public ListViewAdapter(Context context, int layout_id, ArrayList<String> list) {
            super(context, layout_id , list);
        this.layout_id = layout_id;
            this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(layout_id, parent, false);
            }
            TextView tvName = (TextView) convertView.findViewById(R.id.txtDetai);

            tvName.setText(list.get(position));

            return convertView;
    }
}