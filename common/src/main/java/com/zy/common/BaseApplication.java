package com.zy.common;

import android.app.Application;
import android.content.Context;

/**
 * @author:zhangyue
 * @date:2020/9/11
 */
public abstract class BaseApplication extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext=this;
        doSomeing();

        //向后台注册当前设备信息
        registerDevice();
    }

    /**
     * 向后台注册当前设备信息
     */
    private void registerDevice() {

    }

    public static Context getContext(){
        return mContext;
    }

    protected abstract void doSomeing();
}
