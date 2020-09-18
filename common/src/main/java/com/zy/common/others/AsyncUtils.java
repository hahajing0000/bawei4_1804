package com.zy.common.others;

import java.util.concurrent.Executors;

/**
 * @author:zhangyue
 * @date:2020/9/18
 */
public class AsyncUtils {
    private static AsyncUtils instance=new AsyncUtils();
    private AsyncUtils(){}
    public static AsyncUtils getInstance(){
        return instance;
    }

    public void doTaskByCachePool(Runnable runnable){
        Executors.newCachedThreadPool().execute(runnable);
    }

    public void doTaskByFixPool(Runnable runnable){
        Executors.newFixedThreadPool(5).execute(runnable);
    }
}
