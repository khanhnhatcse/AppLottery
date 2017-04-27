package com.swift.nhat.applottery.Utils;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by nhat on 4/24/17.
 */

public class Utils<T> {
    public ArrayList<String> getAllKeys(Map<String, T> map){
        ArrayList<String> list = new ArrayList<>();

        for(Map.Entry<String,  T> entry : map.entrySet()){
            list.add(entry.getKey());
        }
        return list;
    }
 }
