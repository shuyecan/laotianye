package com.laotian.base;

public interface ICallback<T> {
    void onSucceed(T data);

    void onError(String msg);
}
