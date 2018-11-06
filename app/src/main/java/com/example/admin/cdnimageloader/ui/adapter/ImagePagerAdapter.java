package com.example.admin.cdnimageloader.ui.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.admin.cdnimageloader.ui.fragment.ImageSlidePageFragment;

public class ImagePagerAdapter extends FragmentStatePagerAdapter {

    public static String IMAGE_ID = "image_id";
    private static String[] imageIds = {
            "04c4753d05d6d048f10a8f404ff16de4ae54", "147a1069035bf040490bf6d0ab0840e5405b",
            "e59092e70b79204118094ed015df053193b7", "505cca16069a8041c80aeed034910c37ddb4",
            "c320f07e00818046630a1fc0662c27c6f325", "d7056be4059ff04afd0b47401d3892000db9",
            "c562204506b86041bd08f7108d180b4cf70f", "5dafb8fe0818704b56097d80f109ebb427ac",
            "b22c5d4c0cab304c3108b3707c6a2b3866d9", "4aee7ef6039f904931081a30bf98de8818ae"
    };

    public ImagePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new ImageSlidePageFragment();
        Bundle bundle = new Bundle();
        bundle.putString(IMAGE_ID, imageIds[position]);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return imageIds.length;
    }
}

