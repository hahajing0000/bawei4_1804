package com.zy.bll_usercenter;

import android.app.Activity;
import android.app.Service;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Bundle;

import com.zy.bll_usercenter.greendao.DaoMaster;
import com.zy.bll_usercenter.greendao.DaoSession;
import com.zy.common.BaseApplication;
import com.zy.common.log.LogUtils;
import com.zy.imageloader.ImageLoaderManager;
import com.zy.imageloader.PicassoImageLoader;
import com.zy.net.CoreService;

import org.greenrobot.greendao.database.Database;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author:zhangyue
 * @date:2020/9/11
 */
public class MyApplication extends BaseApplication {
    private DaoSession daoSession;
    @Override
    protected void doSomeing() {
        ImageLoaderManager.getInstance().initImageLoader(new PicassoImageLoader());
        //注册设备信息服务
        startService(new Intent(this, CoreService.class));

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "zy-db");
        Database db = helper.getWritableDb();

        // encrypted SQLCipher database
        // note: you need to add SQLCipher to your dependencies, check the build.gradle file
        // DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db-encrypted");
        // Database db = helper.getEncryptedWritableDb("encryption-key");

        daoSession = new DaoMaster(db).newSession();

        this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                LogUtils.getInstance().i("onActivityCreated->"+activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                LogUtils.getInstance().i("onActivityStarted->"+activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                LogUtils.getInstance().i("onActivityResumed->"+activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
                LogUtils.getInstance().i("onActivityPaused->"+activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
                LogUtils.getInstance().i("onActivityStopped->"+activity.getClass().getSimpleName());
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
                LogUtils.getInstance().i("onActivitySaveInstanceState->"+activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                LogUtils.getInstance().i("onActivityDestroyed->"+activity.getClass().getSimpleName());
            }
        });
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
