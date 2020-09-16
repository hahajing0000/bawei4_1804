package com.zy.storage.common;

import android.util.LruCache;

/**
 * @author:zhangyue
 * @date:2020/9/15
 * 内存 缓存 基于 LRU算法
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

    /**
     * 存入值
     * @param key
     * @param value
     */
    public void putValue(String key,V value){
        lruCache.put(key,value);
    }

    /**
     * 取值
     * @param key
     * @return
     */
    public V getValue(String key){
        return lruCache.get(key);
    }

    /**
     * 删除值
     * @param key
     */
    public void removeValue(String key){
        lruCache.remove(key);
    }

    /**
     * 清空
     */
    public void clear(){
        lruCache.evictAll();
    }
}
