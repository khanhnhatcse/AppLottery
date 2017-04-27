package com.swift.nhat.applottery.Rest;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by nhat on 4/24/17.
 */

public interface LotteryApi {
    @GET("/kqxsmn")
    Call<Map<String, Map<String, Map<String, ArrayList<String>>>>> getData();
}
