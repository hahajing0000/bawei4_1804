package com.zy.storage.common;

import android.util.LruCache;

/**
 * @author:zhangyue
 * @date:2020/9/15
 */
public class LRUCacheUtils<V> {
    private static volatile LRUCacheUtils instance=null;
    LruCache<String,V> lruCache=null;
    int maxSize= (int) Runtime.getRuntime().totalMemory()/8;
    private LRUCacheUtils(){
        lruCache=new LruCache<>(maxSize);
    }
    public static LRUCacheUtils getInstance(){
        if (null==instance){
            synchronized (LRUCacheUtils.class){
                if (null==instance){
                    instance=new LRUCacheUtils();
                }
            }
        }
        return instance;
    }

    public Boolean putValue(){
        return true;
    }
}
