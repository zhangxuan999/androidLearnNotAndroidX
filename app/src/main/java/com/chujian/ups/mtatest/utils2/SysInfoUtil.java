package com.chujian.ups.mtatest.utils2;


import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;

public class SysInfoUtil {
//	/***
//	 * 热修复注入代码
//	 */
//	public SysInfoUtil(){
//        System.out.println(com.chujian.sdk.hotfix.CjAntilazyLoad.class);
//    }
	/**
	 * 获取手机的Mac地址
	 * 
	 * @param context
	 * @return
	 */
	@SuppressLint("DefaultLocale")
	public static String getMacAddress(Context context) {
		final WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		String address = wifiInfo.getMacAddress();
		if (address == null) {
			return "";
		} else {
			return address.toUpperCase();
		}
	}
//-------------------------------------------------------------------------------------------------------------------------------

	/**
     * 获取mac地址（获取MAC失败的情况突然变多，返回的Mac地址都是 02:00:00:00:00:00，经百度和技术咨询，原因是谷歌在安卓6.0及以后版本对获取wifi及蓝牙MacAddress 做的改动）
     * @param context
	 * @return
     */
    private static final String marshmallowMacAddress = "02:00:00:00:00:00";
	  private static final String fileAddressMac = "/sys/class/net/wlan0/address";

	  public static String getAdresseMAC(Context context) {
	    WifiManager wifiMan = (WifiManager)context.getSystemService(Context.WIFI_SERVICE) ;
	    WifiInfo wifiInf = wifiMan.getConnectionInfo();

	    if(wifiInf !=null && marshmallowMacAddress.equals(wifiInf.getMacAddress())){
	      String result = null;
	      try {
	        result= getAdressMacByInterface();
	        if (result != null){
	          return result;
	        } else {
	          result = getAddressMacByFile(wifiMan);
	          return result;
	        }
	      } catch (IOException e) {
	        Log.e("MobileAccess", "Erreur lecture propriete Adresse MAC");
	      } catch (Exception e) {
	        Log.e("MobileAcces", "Erreur lecture propriete Adresse MAC ");
	      }
	    } else{
	      if (wifiInf != null && wifiInf.getMacAddress() != null) {
	        return wifiInf.getMacAddress();
	      } else {
	        return "";
	      }
	    }
	    return marshmallowMacAddress;
	  }
	  private static String getAdressMacByInterface(){
		    try {
		      List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
		      for (NetworkInterface nif : all) {
		        if (nif.getName().equalsIgnoreCase("wlan0")) {
		          byte[] macBytes = nif.getHardwareAddress();
		          if (macBytes == null) {
		            return "";
		          }

		          StringBuilder res1 = new StringBuilder();
		          for (byte b : macBytes) {
		            res1.append(String.format("%02X:",b));
		          }

		          if (res1.length() > 0) {
		            res1.deleteCharAt(res1.length() - 1);
		          }
		          return res1.toString();
		        }
		      }

		    } catch (Exception e) {
		      Log.e("MobileAcces", "Erreur lecture propriete Adresse MAC ");
		    }
		    return null;
		  }

	  private static String getAddressMacByFile(WifiManager wifiMan) throws Exception {
		    String ret;
		    int wifiState = wifiMan.getWifiState();

		    wifiMan.setWifiEnabled(true);
		    File fl = new File(fileAddressMac);
		    FileInputStream fin = new FileInputStream(fl);
		    ret = crunchifyGetStringFromStream(fin);
		    fin.close();

		    boolean enabled = WifiManager.WIFI_STATE_ENABLED == wifiState;
		    wifiMan.setWifiEnabled(enabled);
		    return ret;
		  }

		  private static String crunchifyGetStringFromStream(InputStream crunchifyStream) throws IOException {
		    if (crunchifyStream != null) {
		      Writer crunchifyWriter = new StringWriter();

		      char[] crunchifyBuffer = new char[2048];
		      try {
		        Reader crunchifyReader = new BufferedReader(new InputStreamReader(crunchifyStream, "UTF-8"));
		        int counter;
		        while ((counter = crunchifyReader.read(crunchifyBuffer)) != -1) {
		          crunchifyWriter.write(crunchifyBuffer, 0, counter);
		        }
		      } finally {
		        crunchifyStream.close();
		      }
		      return crunchifyWriter.toString();
		    } else {
		      return "No Contents";
		    }
		  }
	
	
	
	
	
	
//--------------------------------------------------------------------------------------------------------------------------------	
	/**
	 * 获取手机ip地址
	 * @param context			
	 * 
	 * @return
	 */
	public static String getIpAddress(Context context) {
		final WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		int ip = wifiInfo.getIpAddress();
		String ipStr=intToIp(ip);
		return ipStr;
	}
	private static String intToIp(int i) {       
	         
          return (i & 0xFF ) + "." +       
        ((i >> 8 ) & 0xFF) + "." +       
        ((i >> 16 ) & 0xFF) + "." +       
        ( i >> 24 & 0xFF) ;  
     }
	/**
	 * 获取手机系统信息
	 * 
	 * @return
	 */
	public static String getSysInfo() {
		String phoneInfo = "Product: " + Build.PRODUCT;
		phoneInfo += ", CPU_ABI: " + Build.CPU_ABI;
		phoneInfo += ", TAGS: " + Build.TAGS;
		phoneInfo += ", VERSION_CODES.BASE: " + Build.VERSION_CODES.BASE;
		phoneInfo += ", MODEL: " + Build.MODEL;
		phoneInfo += ", SDK: " + Build.VERSION.SDK;
		phoneInfo += ", VERSION.RELEASE: " + Build.VERSION.RELEASE;
		phoneInfo += ", DEVICE: " + Build.DEVICE;
		phoneInfo += ", DISPLAY: " + Build.DISPLAY;
		phoneInfo += ", BRAND: " + Build.BRAND;
		phoneInfo += ", BOARD: " + Build.BOARD;
		phoneInfo += ", FINGERPRINT: " + Build.FINGERPRINT;
		phoneInfo += ", ID: " + Build.ID;
		phoneInfo += ", MANUFACTURER: " + Build.MANUFACTURER;
		phoneInfo += ", USER: " + Build.USER;
		return phoneInfo;
	}
	/**
	 * 获取手机手机号
	 * 
	 * @return
	 */
	public static String getPhoneNum(Context context) {
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String phoneId = tm.getLine1Number();
		return phoneId;
	}
	
	/**
	 * 获取时间戳
	 * 
	 * @return
	 */
	public static String getTimeStamp(){
		return String.valueOf(System.currentTimeMillis()/1000);
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
