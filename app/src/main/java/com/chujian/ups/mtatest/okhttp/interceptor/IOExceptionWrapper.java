package com.chujian.ups.mtatest.okhttp.interceptor;

import java.io.IOException;

public class IOExceptionWrapper extends IOException {

    public IOExceptionWrapper(String msg, Throwable cause) {
        super(msg, cause);
    }
}
