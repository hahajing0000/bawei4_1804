package com.zy.bll_usercenter;

import android.app.Service;
import android.app.job.JobService;
import android.content.Intent;

import com.zy.bll_usercenter.greendao.DaoMaster;
import com.zy.bll_usercenter.greendao.DaoSession;
import com.zy.common.BaseApplication;
import com.zy.imageloader.ImageLoaderManager;
import com.zy.imageloader.PicassoImageLoader;
import com.zy.net.CoreService;

import org.greenrobot.greendao.database.Database;

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
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
