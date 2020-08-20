package com.chujian.ups.mtatest.okhttp;



import com.chujian.ups.mtatest.callback.ICallBack;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.ResponseBody;

public class RequestImpl  {

    private RequestImpl() {
    }

    private static RequestImpl requestImpl;

    public static RequestImpl getInstance() {
        if (requestImpl == null) {
            requestImpl = new RequestImpl();
        }
        return requestImpl;
    }


    public void get(Map<String, String> headers, String url, final ICallBack callBack) {
        OkHttpUtils.doGet(headers, url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (callBack != null) {
                    callBack.onException(e);
                    callBack.onFailure(e.getMessage());
                }
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                if (callBack != null) {
                    ResponseBody responseBody = response.body();
                    if (response.code() >= 200 && response.code() < 300) {
                        callBack.onSuccess(new Response(response.code(), responseBody != null ? responseBody.string() : "", null));
                    } else {
                        callBack.onSuccess(new Response(response.code(), "", null));
                    }
                }
            }
        });
    }

    public void post(Map<String, String> headers, String url, Map<String, String> params, final ICallBack callBack) {
        OkHttpUtils.doPost(headers, url, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (callBack != null) {
                    callBack.onException(e);
                    callBack.onFailure(e.getMessage());
                }
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                if (callBack != null) {
                    ResponseBody responseBody = response.body();
                    callBack.onSuccess(new Response(response.code(), responseBody != null ? responseBody.string() : "", null));
                }
            }
        });
    }

    public void requestBody(Map<String, String> headers, String url, String body, final ICallBack callBack) {
        OkHttpUtils.doRequestBody(headers, url, body, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (callBack != null) {
                    callBack.onException(e);
                    callBack.onFailure(e.getMessage());
                }
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                if (callBack != null) {
                    ResponseBody responseBody = response.body();
                    if (response.code() >= 200 && response.code() < 300) {
                        callBack.onSuccess(new Response(response.code(), responseBody != null ? responseBody.string() : "", null));
                    } else {
                        callBack.onSuccess(new Response(response.code(), "", null));
                    }
                }
            }
        });
    }

}
