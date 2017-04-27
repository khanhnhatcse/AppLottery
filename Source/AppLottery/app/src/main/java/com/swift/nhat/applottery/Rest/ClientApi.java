package com.swift.nhat.applottery.Rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.swift.nhat.applottery.Utils.LotteryDeserializer;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by nhat on 4/24/17.
 */

public class ClientApi {
    public static final String BASE_URL = "http://thanhhungqb.tk:8080";
    private static Retrofit retrofit = null;




    public static Retrofit getClient() {
//        GsonBuilder builder = new GsonBuilder();
//        builder.registerTypeAdapter(new TypeToken<Map<String, Map<String, Map<String, ArrayList<String>>>>>() {}.getType(), new LotteryDeserializer());
//        Gson gson = builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
