package com.zy.net;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.zy.net.service.DeviceService;

import androidx.annotation.Nullable;

/**
 * @author:zhangyue
 * @date:2020/9/14
 */
public class CoreService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        //上传设备信息
        DeviceService.getInstance().registerDevice();
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
