package com.zy.common.info;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.zy.common.BaseApplication;

import java.util.Iterator;
import java.util.List;

import static android.content.Context.ACTIVITY_SERVICE;

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

    /**
     * 根据进程ID获取对应APP name
     * @param context 上下文
     * @param pID 进程ID
     * @return
     */
    public String getAppName(Context context,int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = context.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }
}
