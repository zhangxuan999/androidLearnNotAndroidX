package com.chujian.ups.mtatest.util;

import android.os.Build;
import android.text.TextUtils;



import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 获取基础信息的类
 * @author Administrator
 *
 */
public class BaseInfoUtil {
	/***
	 * 热修复注入代码
	 */
	public BaseInfoUtil(){
         
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
