package com.bawp.common;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface GarageAPI {

    @GET("WypPzJCt")
    Call <GarageInfo> loadGarageInfo();
}

