package com.swift.nhat.applottery.Presenter;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by nhat on 4/25/17.
 */

public interface PresenterImpMain {

    void setData(Map<String, Map<String, Map<String, ArrayList<String>>>>  data);
    void setKey(String parent, String current);
    void backup();
}
