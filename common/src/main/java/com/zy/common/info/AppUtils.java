package com.zy.common.info;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.zy.common.BaseApplication;

/**
 * @author:zhangyue
 * @date:2020/9/14
 * @Description 当前应用信息的工具类
 */
public class AppUtils {
    private static AppUtils instance=new AppUtils();
    private AppUtils(){}
    public static AppUtils getInstance(){return instance;}

    /**
     * 获取当前应用的VersionCode
     * @return
     */
    public int getAppVersionCode(){
        Context context = BaseApplication.getContext();
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 获取当前应用的VersionName
     * @return
     */
    public String getAppVersionName(){
        Context context = BaseApplication.getContext();
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
}
