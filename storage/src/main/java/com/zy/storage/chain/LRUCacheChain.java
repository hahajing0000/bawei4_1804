package com.zy.storage.chain;

import com.zy.common.log.LogUtils;
import com.zy.storage.callback.ResultCallback;
import com.zy.storage.common.LRUCacheUtils;

/**
 * @author:zhangyue
 * @date:2020/9/16
 */
public class LRUCacheChain<T> extends StorageChain<T>{
    @Override
    protected void clearData() {
        LRUCacheUtils.getInstance().clear();
    }

    @Override
    protected void removeAtKey(String key) {
        LRUCacheUtils.getInstance().removeValue(key);
    }

    @Override
    protected void saveData(String key, T data) {
        LRUCacheUtils.getInstance().putValue(key,data);
    }

    @Override
    protected void getData(String key, ResultCallback callback) {
        if (callback==null){
            LogUtils.getInstance().w(LRUCacheChain.class.getSimpleName(),"callback is null");
            return;
        }
        callback.Success(LRUCacheUtils.getInstance().getValue(key));
    }
}
