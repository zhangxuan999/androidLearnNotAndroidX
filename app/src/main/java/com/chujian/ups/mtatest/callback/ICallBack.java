package com.chujian.ups.mtatest.callback;


// 回调接口
public interface ICallBack<
        SUCCESS,
        FAILURE,
        CANCEL,
        START,
        UNKNOWN,
        EXCEPTION
        >  {

    void onSuccess(SUCCESS success);

    void onFailure(FAILURE failure);

    void onCancel(CANCEL cancel);

    void onStart(START start);

    void onUnknown(UNKNOWN unknown);

    void onException(EXCEPTION exception);

}
