package com.zy.storage.chain;

import com.zy.common.log.LogUtils;
import com.zy.storage.callback.ResultCallback;
import com.zy.storage.common.DiskLRUCacheUtils;

/**
 * @author:zhangyue
 * @date:2020/9/16
 */
public class DiskLRUCacheChain<T> extends StorageChain<T> {
    @Override
    protected void clearData() {
        DiskLRUCacheUtils.getInstance().clear();
    }

    @Override
    protected void removeAtKey(String key) {
        DiskLRUCacheUtils.getInstance().removeValue(key);
    }

    @Override
    protected void saveData(String key, T data) {
        DiskLRUCacheUtils.getInstance().putValue(key,data);
    }

    @Override
    protected void getData(String key, ResultCallback callback) {
        if (callback==null){
            LogUtils.getInstance().w(DiskLRUCacheChain.class.getSimpleName(),"callback is null");
            return;
        }
        callback.Success(DiskLRUCacheUtils.getInstance().getValue(key));
    }
}
