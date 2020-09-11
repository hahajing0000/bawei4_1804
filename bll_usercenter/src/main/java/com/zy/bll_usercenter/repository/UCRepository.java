package com.zy.bll_usercenter.repository;

import com.zy.bll_usercenter.callback.ResultCallback;
import com.zy.bll_usercenter.contract.UserCenterContract;
import com.zy.bll_usercenter.model.service.UCModel;

/**
 * @author:zhangyue
 * @date:2020/9/9
 */
public class UCRepository extends UserCenterContract.UserCenterRepository {

    @Override
    public void login(String username, String pwd, final ResultCallback callback) {
        //处理多数据源
        mModel.login(username, pwd, callback);
    }

    @Override
    protected void createModel() {
        mModel=new UCModel();
    }
}
