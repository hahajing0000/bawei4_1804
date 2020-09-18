package com.zy.bawei4_1804;

import android.app.Application;
import android.content.Context;

import com.zy.common.BaseApplication;
import com.zy.im.ZIMManager;
import com.zy.im.core.WangYiYunXinIM;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author:zhangyue
 * @date:2020/9/11
 */
public class MyApplication extends BaseApplication {

    @Override
    protected void doSomeing() {
        ZIMManager.getInstance().init(this);
        ZIMManager.getInstance().setIm(new WangYiYunXinIM());
    }
}
