package com.zy.net.service;

import android.util.Log;

import com.zy.common.info.AppUtils;
import com.zy.common.info.DeviceUtils;
import com.zy.common.log.LogEnum;
import com.zy.common.log.LogManager;
import com.zy.common.log.LogType;
import com.zy.common.log.LogUtils;
import com.zy.net.RetrofitFactory;
import com.zy.net.api.DeviceApi;
import com.zy.net.protocol.request.DeviceInfo;
import com.zy.net.protocol.response.BaseEntity;
import com.zy.net.rxjava.BaseObservable;
import com.zy.net.rxjava.BaseObserver;

import io.reactivex.Observable;

/**
 * @author:zhangyue
 * @date:2020/9/14
 */
public class DeviceService {
    private static DeviceService instance=new DeviceService();
    private DeviceService(){}
    public static DeviceService getInstance(){
        return instance;
    }

    /**
     * 注册设备
     */
    public void registerDevice(){
//        DeviceInfo info=new DeviceInfo();
//        info.setAppversioncode(AppUtils.getInstance().getAppVersionCode());
//        info.setAppversionname(AppUtils.getInstance().getAppVersionName());
//        info.setDevicebrand(DeviceUtils.getInstance().getBrand());
//        info.setDevicemanufacturer(DeviceUtils.getInstance().getManufacturer());
//        info.setDevicemodel(DeviceUtils.getInstance().getModel());
//        info.setDevicetype(DeviceUtils.getInstance().getSysType());
//        info.setDisplayparam(DeviceUtils.getInstance().getDisplayparam());
//        info.setImei(DeviceUtils.getInstance().getIMEIOrMEID());
//        info.setSdkversion(String.valueOf(DeviceUtils.getInstance().getSDKVersion()));
//
//        DeviceApi deviceApi = RetrofitFactory.getInstance().create(DeviceApi.class);
//        Observable<BaseEntity<String>> baseEntityObservable = deviceApi.registerDevice(info);
//        BaseObservable.doObservable(baseEntityObservable,new BaseObserver<BaseEntity<String>>(){
//            @Override
//            public void onNext(BaseEntity<String> o) {
//                super.onNext(o);
//                //偷着乐吧
//                LogUtils.getInstance().d("偷着乐吧");
//                LogUtils.getInstance().d(DeviceService.class.getSimpleName(),"偷着乐吧");
//
//
//                LogManager build = new LogManager.Builder().setLogType(LogType.Logcat).build();
//                build.writeLog(LogEnum.E,"出错了");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                super.onError(e);
//                //别着急 再来一次就好了
//
//            }
//        },null);
    }
}
