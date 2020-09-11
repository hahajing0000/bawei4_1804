package com.zy.core.mvp;

/**
 * @author:zhangyue
 * @date:2020/9/9
 */
public abstract class Repository<Model extends IModel> {
    protected Model mModel;

    protected abstract void createModel();

    public Repository(){
        createModel();
    }

    protected void releaseModel(){
        if (mModel!=null){
            mModel.destory();
            mModel=null;
        }
    }
}
