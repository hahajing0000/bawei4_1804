package com.zy.im.callback;

/**
 * @author:zhangyue
 * @date:2020/9/18
 */
public interface ZConnectionListener {
    void onConnected();
    void onDisconnected(int error);
}
