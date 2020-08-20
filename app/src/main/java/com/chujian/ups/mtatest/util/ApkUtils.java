package com.chujian.ups.mtatest.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;


public class ApkUtils {
    private static ApkUtils apkUtils = new ApkUtils();

    public static ApkUtils getInstance() {
        return apkUtils;
    }

     
    public String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

     
    public String getVersionCode(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionCode + "";
        } catch (PackageManager.NameNotFoundException e) {
             e.printStackTrace();
        }
        return 0 + "";
    }

     
    public String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
             e.printStackTrace();
        }
        return null;
    }

     
    public boolean isPackageInstalled(String s,Context context) {
        if (s == null || "".equals(s))
            return false;
        ApplicationInfo info = null;
        try {
            info = context.getPackageManager().getApplicationInfo(s, 0);
            return info != null;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

     
    public String getMetaString(String k,Context context) {
        try {
            ApplicationInfo appInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);

            return appInfo.metaData.getString(k);
        } catch (PackageManager.NameNotFoundException e) {
             e.printStackTrace();
        }
        return "";
    }

     
    public int getMetaInt(String k,Context context) {
        try {
            ApplicationInfo appInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);

            return appInfo.metaData.getInt(k);
        } catch (PackageManager.NameNotFoundException e) {
             e.printStackTrace();
        }
        return -1;
    }

     
    public boolean isDebug(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
             e.printStackTrace();
        }
        return false;
    }

     
    public String getPackageName(Context context) {
        try {
            ApplicationInfo appInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            return appInfo.packageName;
        } catch (PackageManager.NameNotFoundException e) {
             e.printStackTrace();
        }
        return null;
    }

     
    public String getAndroidReleaseVersion() {
        return Build.VERSION.RELEASE;
    }

     
    public String getAndroidSDKVersion() {
        return Build.VERSION.SDK_INT + "";
    }
}
