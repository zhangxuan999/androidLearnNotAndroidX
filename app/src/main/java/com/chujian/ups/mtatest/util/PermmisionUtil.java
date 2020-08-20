package com.chujian.ups.mtatest.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PermmisionUtil {
    // 判断是否需要动态申请权限，并且进行权限申请操作
    public  static boolean applyPermission(Activity activity) {
        boolean needApplyPermission = false;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            List<PermissionInfo> dangerousPermissions = new ArrayList<>();
            try {
                dangerousPermissions = PermmisionUtil.getDangerousPermissions(activity);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            if (dangerousPermissions==null|| dangerousPermissions.size()==0){
                needApplyPermission = false;
                return needApplyPermission;
            }
            //声明一个数组permissions，将需要的权限都放在里面
            List<String> permissionList = new ArrayList<>();

            //逐个判断权限是否已经通过
            String[] mPermissions = new String[dangerousPermissions.size()];
            for (int i = 0 ; i < dangerousPermissions.size();i ++) {
                mPermissions[i]  =dangerousPermissions.get(i).name;
            }
            for (int i = 0; i < mPermissions.length; i++) {
                if (ContextCompat.checkSelfPermission(activity, mPermissions[i]) != PackageManager.PERMISSION_GRANTED) {
                    permissionList.add(mPermissions[i]);//添加还未授予的权限
                }
            }
            int mRequestCode = 100;//权限请求码
            //申请未通过的权限权限
            if (permissionList.size() > 0) {
                needApplyPermission = true;
                ActivityCompat.requestPermissions(activity, mPermissions, mRequestCode);
            }
        }
        return needApplyPermission;
    }

    /**
     * 获取androidmanifet中声明的所有危险权限
     * @param context
     * @return
     * @throws PackageManager.NameNotFoundException
     */
    public static List<PermissionInfo> getDangerousPermissions(Context context)
            throws PackageManager.NameNotFoundException {
        String packageName = context.getPackageName();
        Log.i("AppUtil","packageName = " + packageName);
        List<PermissionInfo> dangerousPermissions = new ArrayList<>();
        PackageManager pm = context.getPackageManager();
        PackageInfo packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);
        if (packageInfo.requestedPermissions != null) {
            for (String requestedPermission : packageInfo.requestedPermissions) {
                try {
                    PermissionInfo permissionInfo = pm.getPermissionInfo(requestedPermission, 0);
                    switch (permissionInfo.protectionLevel & PermissionInfo.PROTECTION_MASK_BASE) {
                        case PermissionInfo.PROTECTION_DANGEROUS:
                            Log.i("AppUtil","permissionInfo.group = " + permissionInfo.group + "name" + permissionInfo.name);

                            dangerousPermissions.add(permissionInfo);
                            break;
                    }
                } catch (PackageManager.NameNotFoundException ignored) {
                    // unknown permission
                }
            }
        }
        Log.i("AppUtil","dangerousPermissions = " + dangerousPermissions.toString());
        return dangerousPermissions;
    }


    public static void exit(final Activity activity){
        try{
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle("权限申请失败");
            builder.setMessage("为了游戏能正常进行，我们需要您同意相关权限");
            builder.setCancelable(false);
            builder.setPositiveButton("退出游戏",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int whichButton) {

                            activity.finish();
                            System.exit(0);
                        }
                    });
            builder.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static boolean onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults, Activity activity) {
        boolean allPermissionGet = true;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (grantResults.length > 0) {
                Log.d("apputil", "grantResults.length:" + grantResults.length + "permissions.length = " + permissions.length);
                for (int i = 0 ;i < grantResults.length ; i ++){
                    if (permissions[i].equalsIgnoreCase("android.permission.ACCESS_BACKGROUND_LOCATION")
                            ||permissions[i].equalsIgnoreCase("android.permission.ACCESS_COARSE_LOCATION")
                            ||permissions[i].equalsIgnoreCase("android.permission.ACCESS_FINE_LOCATION")
                       ){
                        continue;
                    }
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED){
//                        applyPermission();
                        PermmisionUtil.exit(activity);
                        allPermissionGet = false;
                        break;
                    }
                }
            }
        }
        return allPermissionGet;
    }


    // 判断是否需要动态申请权限，但是不进行权限申请操作
    public  static boolean needApplyPermission(Activity activity) {
        boolean needApplyPermission = false;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            List<PermissionInfo> dangerousPermissions = new ArrayList<>();
            try {
                dangerousPermissions = PermmisionUtil.getDangerousPermissions(activity);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            if (dangerousPermissions==null|| dangerousPermissions.size()==0){
                needApplyPermission = false;
                return needApplyPermission;
            }
            //声明一个数组permissions，将需要的权限都放在里面
            List<String> permissionList = new ArrayList<>();

            //逐个判断权限是否已经通过
            String[] mPermissions = new String[dangerousPermissions.size()];
            for (int i = 0 ; i < dangerousPermissions.size();i ++) {
                mPermissions[i]  =dangerousPermissions.get(i).name;
            }
            for (int i = 0; i < mPermissions.length; i++) {
                if (ContextCompat.checkSelfPermission(activity, mPermissions[i]) != PackageManager.PERMISSION_GRANTED) {
                    permissionList.add(mPermissions[i]);//添加还未授予的权限
                }
            }

            if (permissionList.size() > 0) {
                needApplyPermission = true;
            }
        }
        return needApplyPermission;
    }
}
