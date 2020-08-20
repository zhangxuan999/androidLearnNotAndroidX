package com.chujian.ups.mtatest.okhttp;


import android.util.Log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpUtils {

    public static long CONNECTTIMEOUT = 5;
    public static long READTIMEOUT = 5;
    public static long WRITETIMEOUT = 5;

    private OkHttpUtils() {
    }

    private static OkHttpClient client;

    public static OkHttpClient getInstance() {
        if (client == null) {
            HttpLoggingInterceptor interceptor =
                    new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                        @Override
                        public void log(String message) {
                            try {
                                Log.i("ChuJianSDK", message);
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e("ChuJianSDK", message);
                            }
                        }
                    });

            //包含header、body数据
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client = new OkHttpClient.Builder()
                    .connectTimeout(CONNECTTIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(READTIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(WRITETIMEOUT, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .build();
        }
        return client;
    }

    public static void doGet(Map<String, String> headers, String url, Callback callback) {
                //创建OkHttpClient请求对象
                OkHttpClient okHttpClient = getInstance();

                Request.Builder request = new Request.Builder();
                if (headers != null) {
                    for (String k : headers.keySet()) {
                        request.addHeader(k, headers.get(k));
                    }
                }
                //创建Request
                try {
                    request.url(url);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    callback.onFailure(null, new IOException(e.getLocalizedMessage()));
                    return;
                }
                //得到Call对象
                Call call = okHttpClient.newCall(request.build());
                //执行异步请求
                call.enqueue(callback);

    }

    public static void doPost(
            Map<String, String> headers,
            String url,
            Map<String, String> params,
            Callback callback) {

                Request.Builder request = new Request.Builder();
                if (headers != null) {
                    for (String k : headers.keySet()) {
                        request.addHeader(k, headers.get(k));
                    }
                }
                try {
                    request.url(url);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                    callback.onFailure(null, new IOException(e.getLocalizedMessage()));
                    return;
                }
                FormBody.Builder builder = new FormBody.Builder();

                for (String key : params.keySet()) {
                    builder.add(key, params.get(key));
                }
                request.post(builder.build());
                getInstance().newCall(request.build()).enqueue(callback);
    }

    public static void doRequestBody(
            Map<String, String> headers,
            String url,
            String body,
            Callback callback) {

        Request.Builder request = new Request.Builder();
        if (headers != null) {
            for (String k : headers.keySet()) {
                request.addHeader(k, headers.get(k));
            }
        }
        try {
            request.url(url);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            callback.onFailure(null, new IOException(e.getLocalizedMessage()));
            return;
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), body);
        request.post(requestBody);
        getInstance().newCall(request.build()).enqueue(callback);
    }
}