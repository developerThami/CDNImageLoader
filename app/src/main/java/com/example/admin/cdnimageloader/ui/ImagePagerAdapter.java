package com.example.admin.cdnimageloader.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ImagePagerAdapter extends FragmentStatePagerAdapter {

    private int numPages = 0;

    public ImagePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    @Override
    public Fragment getItem(int position) {
        return new ImageSlidePageFragment();
    }

    @Override
    public int getCount() {
        return numPages;
    }
}

