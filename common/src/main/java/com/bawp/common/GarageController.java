package com.bawp.common;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GarageController {

    static final String BASE_URL = "https://pastebin.com/raw/";
    private final RetrofitCallBack retrofitCallBack;

    public GarageController(RetrofitCallBack retrofitCallBack) {
        this.retrofitCallBack = retrofitCallBack;
    }

    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GarageAPI garageAPI = retrofit.create(GarageAPI.class);

        Call<GarageInfo> call = garageAPI.loadGarageInfo();

        call.enqueue(new Callback<GarageInfo>() {
            @Override
            public void onResponse(Call<GarageInfo> call, Response<GarageInfo> response) {
                if(response.isSuccessful()) {
                    GarageInfo changesList = response.body();
                    StringBuilder stringBuilder = new StringBuilder();

                    for (int i = 0; i < changesList.getCars().size(); i++) {
                        stringBuilder.append(changesList.getCars().get(i)).append("\n");
                        retrofitCallBack.infoFromRetrofit(stringBuilder);
                    }
                } else {
                    Log.d("jjjj", "retrofit fail");
                }
            }

            @Override
            public void onFailure(Call<GarageInfo> call, Throwable t) {

            }
        });
    }
}

/*
  static final String BASE_URL = "https://pastebin.com/raw/";

    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GarageAPI garageAPI = retrofit.create(GarageAPI.class);

        Call<List<GarageInfo>> call = garageAPI.loadGarageInfo();
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<List<GarageInfo>> call, Response<List<GarageInfo>> response) {
       if(response.isSuccessful()) {
                    GarageInfo changesList = response.body();
                    StringBuilder stringBuilder = new StringBuilder();

                    for (int i = 0; i < changesList.getCars().size(); i++) {
                        stringBuilder.append(changesList.getCars().get(i)).append("\n");
                        retrofitCallBack.infoFromRetrofit(stringBuilder);
                    }
                } else {
                    Log.d("jjjj", "retrofit fail");
    }

    @Override
    public void onFailure(Call<List<GarageInfo>> call, Throwable t) {
        t.printStackTrace();
    }





      call.enqueue(new Callback<GarageInfo>() {
            @Override
            public void onResponse(Call<GarageInfo> call, Response<GarageInfo> response) {
                if(response.isSuccessful()) {
                    GarageInfo changesList = response.body();
                    StringBuilder stringBuilder = new StringBuilder();

                    for (int i = 0; i < changesList.getCars().size(); i++) {
                        stringBuilder.append(changesList.getCars().get(i)).append("\n");
                        retrofitCallBack.infoFromRetrofit(stringBuilder);
                    }
                } else {
                    Log.d("jjjj", "retrofit fail");
                }
            }

            @Override
            public void onFailure(Call<GarageInfo> call, Throwable t) {

            }
        });
    }
 */