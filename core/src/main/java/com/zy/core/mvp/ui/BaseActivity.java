package com.zy.core.mvp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zy.core.mvp.Presenter;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author:zhangyue
 * @date:2020/9/9
 */
public abstract class BaseActivity<P extends Presenter> extends AppCompatActivity {
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createPresenter();

        setContentView(getLayoutId());

        initView(savedInstanceState);
        initData();
        initEvent();
    }

    /**
     * 初始化事件
     */
    protected abstract void initEvent();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化视图
     * @param savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 设置layout id
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 创建P层实例
     */
    protected abstract void createPresenter();

    /**
     * 查找资源
     * @param viewId
     * @param <T>
     * @return
     */
    protected <T extends View> T $(@IdRes int viewId){
        return findViewById(viewId);
    }

    /**
     * 提示消息
     * @param msg
     */
    protected void showMsg(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

}
