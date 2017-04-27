package com.swift.nhat.applottery.Activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.swift.nhat.applottery.Adapters.ListViewAdapter;
import com.swift.nhat.applottery.R;

import java.util.ArrayList;

/**
 * Created by nhat on 4/25/17.
 */

public class DetailFragment extends Fragment {

    private ArrayList<String> list;
    private ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        Bundle bundle = getArguments();
        list = bundle.getStringArrayList("data");
        Log.d("Test", list.toString());
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        listView = (ListView) view.findViewById(R.id.lvDetail);

        ListViewAdapter listViewAdapter = new ListViewAdapter(this.getContext(), R.layout.detai_item, list);
        listView.setAdapter(listViewAdapter);
        return view;
    }
}
