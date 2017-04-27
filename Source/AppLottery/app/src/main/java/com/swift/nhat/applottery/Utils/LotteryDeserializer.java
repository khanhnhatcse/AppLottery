package com.swift.nhat.applottery.Utils;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nhat on 4/24/17.
 */

public class LotteryDeserializer implements JsonDeserializer<Map<String, Map<String, Map<String, ArrayList<String>>>>> {


    @Override
    public Map<String, Map<String, Map<String, ArrayList<String>>>> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Map<String, Map<String, Map<String, ArrayList<String>>>> list = new HashMap<>();
        JsonObject parentJsonObject = json.getAsJsonObject();
        for(Map.Entry<String, JsonElement> entry : parentJsonObject.entrySet()){
            Map<String, Map<String, ArrayList<String>>> childMap = new HashMap<>();
            for(Map.Entry<String, JsonElement> entry1 : entry.getValue().getAsJsonObject().entrySet()){

                Map<String, ArrayList<String>> childMap1 = new HashMap<>();
                for(Map.Entry<String, JsonElement> entry2 : entry1.getValue().getAsJsonObject().entrySet()){
                    ArrayList<String> entry3 = new Gson().fromJson(entry2.getValue().getAsJsonArray(), new TypeToken<List<String>>(){}.getType());
                    childMap1.put(entry2.getKey(), entry3);
                }
                childMap.put(entry1.getKey(), childMap1);
            }
            list.put(entry.getKey(), childMap);
        }
        return list;
    }
}
