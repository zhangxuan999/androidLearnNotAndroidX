package com.chujian.ups.mtatest.callback;


public interface InitCallBack<T, E>  {

    void onSuccess(T t);

    void onFailure(E e);

}
