package com.example.admin.cdnimageloader.ui;

import com.example.admin.cdnimageloader.api.response.IPLookUpInformation;

public interface AppContract {

    interface Presenter{
        void getIPInformation();
    }

    interface View{
        void showImageTitle(IPLookUpInformation information);
    }
}
