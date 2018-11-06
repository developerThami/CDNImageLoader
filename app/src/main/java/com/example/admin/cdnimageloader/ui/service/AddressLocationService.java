package com.example.admin.cdnimageloader.ui.service;


import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AddressLocationService extends IntentService {

    public static final String LOCATION = "location";

    public AddressLocationService() {
        super("AddressLocationService");
    }

    @Override
    protected void onHandleIntent( Intent intent) {
        Location location = intent.getParcelableExtra(LOCATION);

        List<Address> addresses = new ArrayList<>();
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(),
                    location.getLongitude(), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        getAddress(addresses.get(0));
    }

    Observable<Address> getAddress(Address address){
        return Observable.just(address)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
