package com.swift.nhat.applottery.Presenter;

import com.swift.nhat.applottery.View.ViewMain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nhat on 4/25/17.
 */

public class PresenterMain implements PresenterImpMain {

    private ViewMain view;
    private Map<String, Map<String, Map<String, ArrayList<String>>>> data;

    public PresenterMain(ViewMain view) {
        this.view = view;
        this.data = new HashMap<>();
    }



    @Override
    public void setData(Map<String, Map<String, Map<String, ArrayList<String>>>> data) {
        this.data = data;
    }

    @Override
    public void setKey(String parent, String current) {
        Map<String, ArrayList<String>> map = this.data.get(parent).get(current);
        view.onDetai(map);
    }
    @Override
    public void backup() {

    }

}
