package com.example.admin.cdnimageloader.ui;

import android.util.Log;

import com.example.admin.cdnimageloader.api.Api;
import com.example.admin.cdnimageloader.api.response.IPLookUpInformation;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Presenter implements AppContract.Presenter{

    private AppContract.View view;

    public Presenter(AppContract.View view) {
        this.view = view;
    }

    @Override
    public void getIPInformation() {
        Api.start().lookUpIP(getLocalIpAddress())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<IPLookUpInformation>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(IPLookUpInformation value) {
                        view.showImageTitle(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
                 en.hasMoreElements(); ) {
                NetworkInterface networkInterface = en.nextElement();
                for (Enumeration<InetAddress> networkInterfaceInetAddresses = networkInterface
                        .getInetAddresses(); networkInterfaceInetAddresses.hasMoreElements(); ) {
                    InetAddress inetAddress = networkInterfaceInetAddresses.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress().trim();
                    }
                }
            }
        } catch (Exception ex) {
            Log.e("IP Address", ex.toString());
        }
        return null;
    }
}
