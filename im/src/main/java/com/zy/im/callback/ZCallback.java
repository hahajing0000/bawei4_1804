package com.zy.im.callback;

/**
 * @author:zhangyue
 * @date:2020/9/18
 */
public interface ZCallback<T> {
    void onSuccess(T t);
    void onProgress(int progress,String status);
    void onError(int code,String message);
}
