package com.zy.bll_usercenter;

import android.app.Service;
import android.app.job.JobService;
import android.content.Intent;

import com.zy.common.BaseApplication;
import com.zy.imageloader.ImageLoaderManager;
import com.zy.imageloader.PicassoImageLoader;
import com.zy.net.CoreService;

/**
 * @author:zhangyue
 * @date:2020/9/11
 */
public class MyApplication extends BaseApplication {
    @Override
    protected void doSomeing() {
        ImageLoaderManager.getInstance().initImageLoader(new PicassoImageLoader());
        //注册设备信息服务
        startService(new Intent(this, CoreService.class));
    }
}
