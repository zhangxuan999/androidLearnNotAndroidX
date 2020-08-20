package com.chujian.ups.mtatest.net;

import android.util.Log;

import com.chujian.ups.mtatest.callback.ICallBack;
import com.chujian.ups.mtatest.okhttp.RequestImpl;
import com.chujian.ups.mtatest.util.BaseInfoUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class UpsNet {

    static final String app_key = "dba6091f35a64dcabab353a43a97da38";
//    static final String baseUrl = "https://testin.ups.16801.com/upsmanager/";//国内测试环境
    static final String baseUrl = "https://upsdev.16801.com:888/upsmanager/";//开发环境

    public static String format(Map<String, String> paramMap) {

        StringBuffer bf = new StringBuffer();
        Map<String, String> treeMap = new TreeMap<>(paramMap);
        treeMap.remove("signature");

        try {
            for (Map.Entry<String, String> entry : treeMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (bf.length() > 0) {
                    bf.append("&");
                }

                bf.append(key).append("=").append(URLEncoder.encode(value, "UTF-8"));
            }
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            Log.e("CertificateSDK","Encode parameters failed.");
        }

        String body = bf.toString();

        // 空格和星号转义, 空格经过URLEncoder后，会转换为+
        body = body.replaceAll("\\+", "%20").replaceAll("\\*", "%2A");
        return body;
    }

    public static void modifyMtaStatus(String client_id,String operate,String umid, ICallBack ICallBack) {
        String url = baseUrl + "app/modify_mtatestsetting_status";

        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("client_id",client_id);
        hashMap.put("operate",operate);
        hashMap.put("umids",umid);
        String signature = BaseInfoUtil.MD5(format(hashMap) + app_key);

        try {
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("client_id",client_id);
            jsonObject1.put("operate",operate);
            jsonObject1.put("umids",umid);
            jsonObject1.put("signature",signature);
            RequestImpl.getInstance().requestBody(null, url, jsonObject1.toString(), ICallBack);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private static final long interval = 90*24*3600*1000L;//三个月
    public static void queryMtaResult(String client_id, String umid, Date startTime, Date endTime, ICallBack ICallBack) {
        String url = baseUrl + "app/query_mtatestsetting";
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("client_id",client_id);
        hashMap.put("umids",umid);
        hashMap.put("categories","SDKActivatedEvent,SplashStartRequestedEvent,ResourceServerVisitRequestedEvent,AppVersionCheckRequestedEvent,ResourceLoadingRequestedEvent,ResourceUpdateStartedEvent,ResourceUpdatedEvent,SDKLoadedEvent,RegisterPageDisplayRequestedEvent,RegisterPageDisplayedEvent,RegisteredEvent,LoginPageDisplayRequestedEvent,LoginPageDisplayedEvent,LoggedinEvent,NoticePageDisplayedEvent,GameServerListedEvent,GameServerLoggedinEvent,AccountLogoutEvent,RoleCreatedEvent,RoleLoginRequestedEvent,RoleLoggedinEvent,RoleLogoutEvent,PayRequestedEvent,PayPageDisplayRequestedEvent,PayPageDisplayedEvent,PayMethodConfirmedEvent,GravitationCheckedEvent,GameBackgroundSwitchedEvent,GameForegroundSwitchedEvent");
        Log.d("getTime()", "choice System.currentTimeMillis(): " + System.currentTimeMillis());
        long threeM = System.currentTimeMillis()- interval;
        Log.d("getTime()", "choice System.currentTimeMillis() threeM: " + threeM);
        if (startTime != null){
            hashMap.put("start_time",getTime(startTime));
        }else {
            hashMap.put("start_time", getTime(new Date((System.currentTimeMillis()- interval))));
        }

        if (endTime != null){
            hashMap.put("end_time",getTime(endTime));
        }else {
            hashMap.put("end_time",getTime(new Date(System.currentTimeMillis())));
        }

        hashMap.put("page",1+"");
        hashMap.put("pageSize",100+"");
        hashMap.put("distinct","true");
        String signature = BaseInfoUtil.MD5(format(hashMap) + app_key);

        try {
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("client_id",client_id);
            jsonObject1.put("umids",umid);
            jsonObject1.put("categories",hashMap.get("categories"));
            jsonObject1.put("start_time",hashMap.get("start_time"));
            jsonObject1.put("end_time",hashMap.get("end_time"));
            jsonObject1.put("page",hashMap.get("page"));
            jsonObject1.put("pageSize",hashMap.get("pageSize"));
            jsonObject1.put("distinct",hashMap.get("distinct"));
            jsonObject1.put("signature",signature);
            RequestImpl.getInstance().requestBody(null, url, jsonObject1.toString(), ICallBack);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }


}
