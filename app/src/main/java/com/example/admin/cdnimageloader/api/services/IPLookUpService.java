package com.example.admin.cdnimageloader.api.services;

import com.example.admin.cdnimageloader.api.response.IPLookUpInformation;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IPLookUpService {
    @GET("/")
    Observable<IPLookUpInformation> getIp(@Query("ip") String ip , @Query("auth") String authKey);
}
