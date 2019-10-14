package com.adgyde.example;

import android.app.Application;
import android.util.Log;

import com.adgyde.android.AdGyde;


public class ExampleApplication extends Application implements Constants {
    @Override
    public void onCreate() {
       super.onCreate();
        Log.d(TAG, "ExampleApplication.onCreate()");
        // Initialize AdGyde SDK with appkey & default channel id "Organic".
        // When applictaion is installed from Google Play Store without any campaign the Channel will be Organic as specified in Init Function
        // In case the applictaion is installed through a campaign link then the Default channel will be overriden and value from the campaign link will be passed.
        AdGyde.init(this, "Your_App_Key", "Organic");
        AdGyde.setDebugEnabled(true);

        //For Collecting IMEI
        AdGyde.allowPermissionIMEI(this,true);

    }

}