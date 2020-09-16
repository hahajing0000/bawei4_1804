package com.zy.storage.chain;

import com.zy.storage.callback.ResultCallback;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author:zhangyue
 * @date:2020/9/16
 * 存储管理类
 */
public class StorageChainManager<T> {

    /**
     * 缓存各个存储链资源
     */
    private static ConcurrentHashMap<String,StorageChain> chainMap;

    //内存
    private LRUCacheChain memoryCacheChain=null;
    //磁盘
    private DiskLRUCacheChain diskLRUCacheChain=null;

    private static volatile StorageChainManager instance=null;
    private StorageChainManager(){
        chainMap=new ConcurrentHashMap<>();
    }
    public static StorageChainManager getInstance(){
        if (null==instance){
            synchronized (StorageChainManager.class){
                if (null==instance){
                    instance=new StorageChainManager();
                }
            }
        }
        return instance;
    }

    /**
     * 存值
     * @param key
     * @param value
     */
    public void putValue(String key,T value){
        if (!chainMap.containsKey(key)){
            initChain(key);
        }
        memoryCacheChain.putValue(key,value);
    }

    /**
     * 初始化链
     * @param key
     */
    private StorageChain initChain(String key) {
        memoryCacheChain=new LRUCacheChain();
        diskLRUCacheChain=new DiskLRUCacheChain();

        memoryCacheChain.setNextChain(diskLRUCacheChain);

        chainMap.put(key,memoryCacheChain);
        return diskLRUCacheChain;
    }

    /**
     * 取值
     * @param key
     * @param callback
     */
    public void getValue(String key, ResultCallback callback){
        if (chainMap.containsKey(key)){
            chainMap.get(key).getValue(key,callback);
        }
    }

    /**
     * 添加链节点
     * @param key key
     * @param storageChain 存储链节点的实现类实例
     */
    public void addChain(String key,StorageChain storageChain){
        if (chainMap.containsKey(key)){
            StorageChain storageChain1 = chainMap.get(key);
            while (storageChain1!=null){
                storageChain1=storageChain.nextChain;
            }
            storageChain1.setNextChain(storageChain);
        }else{
            StorageChain storageChain1 = initChain(key);
            storageChain1.setNextChain(storageChain);
        }
    }

}
