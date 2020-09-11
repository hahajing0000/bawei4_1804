package com.zy.core.mvp;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author:zhangyue
 * @date:2020/9/9
 */
public abstract class Presenter<R extends Repository,V extends IView> {
    /**
     * 数据仓库
     */
    protected R mRepository;

    /**
     * 软引用 处理V层可能出现的内存泄漏问题
     */
    protected SoftReference<V> mView;

    /**
     * 创建数据仓库实例
     */
    protected abstract void createRepository();

    public Presenter(V v){
        createRepository();
        mView=new SoftReference<>(v);
    }
}
