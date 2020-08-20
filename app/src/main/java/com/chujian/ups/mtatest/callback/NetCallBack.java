package com.chujian.ups.mtatest.callback;


import com.chujian.ups.mtatest.okhttp.Response;

public class NetCallBack extends CallBack<
        Response,
        String,
        Object,
        Object,
        Object,
        Throwable
        > {
    @Override
    public void onSuccess(Response response) {
        super.onSuccess(response);
    }

    @Override
    public void onFailure(String s) {
        super.onFailure(s);
    }

    @Override
    public void onCancel(Object o) {
        super.onCancel(o);
    }

    @Override
    public void onStart(Object o) {
        super.onStart(o);
    }

    @Override
    public void onUnknown(Object o) {
        super.onUnknown(o);
    }

    @Override
    public void onException(Throwable throwable) {
        super.onException(throwable);
    }
}
