package com.chujian.ups.mtatest.utils2;

import android.content.Context;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

import com.chujian.sdk.ChuJianSDK;
import com.chujian.sdk.SDKParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Parameters {
	
	/**
	 * 获取adcode
	 */
 private static String AdCODE;
 
 private String deviceid;
 
 private static  String IMEI;
 
 private static String XiaoXi_Mac;
 
 private static  String MAC;
 
 private static String IMSI;
 
 private static  String ANDROID_ID;
 
	public static String adcode(){
		
	InputStream inputStream = null;
	try {
		inputStream = ChuJianSDK.getInstance().getContext().getAssets().open("sinfo.dat");
	} catch (IOException e) {
		e.printStackTrace();
	}
	if (inputStream != null) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		AdCODE = sb.toString();
	  }
	    return AdCODE;
	}
	
	/**
	 * 获取deviceid
	 */
	public static String deviceid(){
		
	
    	TelephonyManager telephonyManager = (TelephonyManager) ChuJianSDK.getInstance().getContext().getSystemService(Context.TELEPHONY_SERVICE);
    	IMEI = telephonyManager.getDeviceId();
    	IMSI = telephonyManager.getSubscriberId();
    	MAC = SysInfoUtil.getAdresseMAC(ChuJianSDK.getInstance().getContext());
    	ANDROID_ID=Secure.getString(ChuJianSDK.getInstance().getContext().getContentResolver(), Secure.ANDROID_ID);	
		
    	  String deviceInfo=getDiviceInfo();
    		if (MAC!=null) {
    			deviceInfo=deviceInfo+MAC;
    		}
    		if(IMEI!=null){
    			deviceInfo=deviceInfo+IMEI;
    		}
    		if(IMSI!=null){
    			deviceInfo=deviceInfo+IMSI;
    		}
    		if(ANDROID_ID!=null){
    			deviceInfo=deviceInfo+ANDROID_ID;
    		}
    		XiaoXi_Mac= MD5(deviceInfo);
    		String deviceid_str=getdeviceID();
         	String deviceid=MD5(deviceid_str);
         	
		return deviceid;
		
	}
	public static String getdeviceID(){
		String deviceID_str;
	    int channel=ChuJianSDK.getInstance().getCurrChannel();
	    if(channel==6){
	    	  SDKParams params=ChuJianSDK.getInstance().getSDKParams();
	  	      String gameid=params.getString("gameId");
	  		  deviceID_str=XiaoXi_Mac+"_"+IMEI+"_"+gameid.substring(16);
	    }else{
//	    SDKParams params=ChuJianSDK.getInstance().getSDKParams();
//	    String gameid=params.getString("gameId");
	         deviceID_str=XiaoXi_Mac+"_"+IMEI+"_"+ChuJianSDK.getInstance().getAppID();
	    }
		return deviceID_str;
		
	}
    public static String getDiviceInfo(){
    	String deviceIndo = "35" + //we make this look like a valid IMEI 
    	Build.BOARD.length()%10 + 
    	Build.BRAND.length()%10 + 
    	Build.CPU_ABI.length()%10 + 
    	Build.DEVICE.length()%10 + 
    	Build.DISPLAY.length()%10 + 
    	Build.HOST.length()%10 + 
    	Build.ID.length()%10 + 
    	Build.MANUFACTURER.length()%10 + 
    	Build.MODEL.length()%10 + 
    	Build.PRODUCT.length()%10 + 
    	Build.TAGS.length()%10 + 
    	Build.TYPE.length()%10 + 
    	Build.USER.length()%10 ; //13 digits
    	return deviceIndo;
    }
    /**
	 * 将字串MD5
	 * 
	 * @param param
	 * @return
	 */
	public static String MD5(String param) {
		byte[] hash;
		try {
			hash = MessageDigest.getInstance("MD5").digest(param.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
		StringBuilder hex = new StringBuilder(hash.length * 2);
		for (byte b : hash) {
			if ((b & 0xFF) < 0x10)
				hex.append("0");
			hex.append(Integer.toHexString(b & 0xFF));
		}
		return hex.toString();
	}
}
