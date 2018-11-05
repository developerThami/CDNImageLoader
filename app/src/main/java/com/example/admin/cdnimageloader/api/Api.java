package com.example.admin.cdnimageloader.api;

import com.example.admin.cdnimageloader.api.services.IPLookUpService;
import com.example.admin.cdnimageloader.api.response.IPLookUpInformation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static final String BASE_URL = "https://ipfind.co/";
    private static final String AUTH_KEY = "2e989bf8-d2c6-4559-b62b-a99f5f75fc32";

    private static Api instance;
    private IPLookUpService ipLookUpService;

    private Api() {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson)).build();

        ipLookUpService = retrofit.create(IPLookUpService.class);
    }

    public static Api start() {
        return instance = instance == null ? new Api() : instance;
    }

    public Observable<IPLookUpInformation> lookUpIP(String ip) {
        return ipLookUpService.getIp(ip, AUTH_KEY);
    }
}
