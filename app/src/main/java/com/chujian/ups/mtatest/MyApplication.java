package com.chujian.ups.mtatest;

import android.app.Application;
import android.util.Log;

import com.bun.miitmdid.core.JLibrary;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("ChuJianSDK", "InitEntry");
        try {
            JLibrary.InitEntry(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
