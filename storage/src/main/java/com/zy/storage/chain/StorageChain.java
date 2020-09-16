package com.zy.storage.chain;

import com.zy.storage.callback.ResultCallback;

/**
 * @author:zhangyue
 * @date:2020/9/16
 */
public abstract class StorageChain<T> {

    /**
     * 链名称
     */
    protected String chainName;

    /**
     * 上一节点
     */
    protected StorageChain previousChain;

    /**
     * 下一节点
     */
    protected StorageChain nextChain;

    /**
     * 设置下游链（自动设置上游链节点）
     * @param storageChain
     */
    public void setNextChain(StorageChain storageChain){
        this.nextChain=storageChain;
        storageChain.previousChain=this;
    }

    /**
     * 存入值
     * @param key
     * @param data
     */
    public void putValue(String key,T data){
        saveData(key,data);
        if (this.nextChain!=null){
            this.nextChain.putValue(key,data);
        }
    }

    /**
     * 获取值
     * @param key
     * @param callback
     */
    public void getValue(final String key, final ResultCallback<T> callback){
        getData(key,new ResultCallback<T>(){

            @Override
            public void Success(T data) {
                if (data==null){
                    nextChain.getValue(key,callback);
                }
                else{
                    if (previousChain!=null){
                        previousChain.putValue(key,data);
                    }

                    callback.Success(data);
                }
            }
        });
    }

    /**
     * 根据key删除值
     * @param key
     */
    public void removeValue(String key){
        removeAtKey(key);
        if (this.nextChain!=null){
            this.nextChain.removeValue(key);
        }
    }

    /**
     * 清空值
     */
    public void clear(){
        clearData();
        if (this.nextChain!=null){
            this.nextChain.clear();
        }
    }

    /**
     * 清空数据
     * 交由子类实现
     */
    protected abstract void clearData();

    /**
     * 删除值
     * 该方法子类实现
     * @param key
     */
    protected abstract void removeAtKey(String key);

    /**
     * 存入值
     * 该方法由子类实现
     * @param key
     * @param data
     */
    protected abstract void saveData(String key, T data);

    /**
     * 获取值
     * 该方法由子类实现
     * @param key
     * @param callback
     */
    protected abstract void getData(String key, ResultCallback callback);
}
