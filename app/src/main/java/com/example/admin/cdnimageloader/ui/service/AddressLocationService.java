package com.example.admin.cdnimageloader.ui.service;


import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.content.LocalBroadcastManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddressLocationService extends IntentService {

    public static final String LOCATION = "location";
    public static final String ACTION_SEND_ADDRESS = "intent.send.Address";
    public static final String ADDRESS_EXTRA = ".Address";

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

        sendAddress(addresses);
    }

    public void sendAddress(List<Address> address){
        Intent intent = new Intent();
        intent.setAction(ACTION_SEND_ADDRESS);
        intent.putExtra(ADDRESS_EXTRA , address.get(0));
        sendBroadcast(intent);
    }
}
