package com.chujian.ups.mtatest.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.chujian.ups.mtatest.callback.InitCallBack;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;

public class GoogleIdUtils  {

    private static GoogleIdUtils googleIdUtils = new GoogleIdUtils();

    public static GoogleIdUtils getInstance() {
        return googleIdUtils;
    }

    public void getGoogleID(final Context context, final  InitCallBack<String, String> callBack) {

        TaskExecutor.getInstance().executeOnThread(new Runnable() {
            @Override
            public void run() {
                int gmsAvaliable = GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context);
                if (gmsAvaliable == ConnectionResult.SUCCESS) {
                    AdvertisingIdClient.Info advertisingIdInfo = null;
                    try {
                        advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
                    } catch (Exception e) {
                    }
                    String googleAdId = "";
                    if (advertisingIdInfo != null) {
                        googleAdId = advertisingIdInfo.getId();
                    }
                    Log.i("ChuJianSDK","googleAdId = " + googleAdId);
                    callBack.onSuccess(googleAdId);
                }else{
                    callBack.onSuccess("");
                }
            }
        });
    }
}
