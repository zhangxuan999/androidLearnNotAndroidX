package com.chujian.ups.mtatest.okhttp;

import android.support.annotation.NonNull;

public class Response {

    // -1 exception
    public int code;
    public String body;
    public String errorMessage;

    public Response(int code, String body, String errorMessage) {
        this.code = code;
        this.body = body;
        this.errorMessage = errorMessage;
    }

    @NonNull
    @Override
    public String toString() {
        return "code:"+code+"; body:"+body+";errorMsg:"+errorMessage;
    }
}
