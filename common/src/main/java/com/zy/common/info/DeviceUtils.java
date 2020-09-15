package com.zy.common.info;

import android.net.Uri;
import android.os.Build;

import com.zy.common.BaseApplication;

import retrofit2.http.PUT;

/**
 * @author:zhangyue
 * @date:2020/9/14
 * @Description 获取设备信息的工具类
 */
public class DeviceUtils {
    private static volatile DeviceUtils instance=null;
    private DeviceUtils(){}
    public static DeviceUtils getInstance(){
        if (null==instance){
            synchronized (DeviceUtils.class){
                if (null==instance){
                    instance=new DeviceUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 获取设备品牌
     * @return
     */
    public String getBrand(){
        return Build.BRAND;
    }

    /**
     * 获取设备厂商
     * @return
     */
    public String getManufacturer(){
        return Build.MANUFACTURER;
    }

    /**
     * 获取设备机型
     * @return
     */
    public String getModel(){
        return Build.MODEL;
    }

    /**
     * 获取系统的编译类型 ENG USERDEBUG USER
     * @return
     */
    public String getSysType(){
        return Build.TYPE;
    }

    /**
     * 获取显示参数
     * @return
     */
    public String getDisplayparam(){
        return Build.DISPLAY;
    }

    /**
     * 获取SDK 版本
     * @return
     */
    public int getSDKVersion(){
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取IMEI MEID
     * @return
     */
    public String getIMEIOrMEID(){
        return IMEIUtils.getDeviceId(BaseApplication.getContext());
    }
}
