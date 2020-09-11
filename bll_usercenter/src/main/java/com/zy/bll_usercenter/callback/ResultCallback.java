package com.zy.bll_usercenter.callback;

/**
 * @author:zhangyue
 * @date:2020/9/9
 */
public interface ResultCallback {
    void Success(Object... objects);
    void Failed(Throwable throwable);
}
