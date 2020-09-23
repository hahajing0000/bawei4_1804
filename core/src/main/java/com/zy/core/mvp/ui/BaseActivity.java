package com.zy.core.mvp.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zy.core.mvp.InjectP;
import com.zy.core.mvp.Presenter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author:zhangyue
 * @date:2020/9/9
 */
public abstract class BaseActivity<P extends Presenter> extends AppCompatActivity {

    protected List<Presenter> mPresenters;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());

        initPresenters();

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

    /**
     * 创建P层实例
     */
    private void initPresenters(){
        mPresenters=new ArrayList<>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field:fields){
            InjectP annotation = field.getAnnotation(InjectP.class);
            if (annotation!=null){

                try {
                    Class<? extends Presenter> type= (Class<? extends Presenter>) field.getType();
                    Constructor<?>[] constructors = type.getConstructors();
                    Presenter presenter =null;
                    for (Constructor constructor:constructors){
                        presenter= (Presenter) constructor.newInstance(this);
                        break;
                    }
                    field.setAccessible(true);
                    field.set(this,presenter);
                    mPresenters.add(presenter);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
