package com.zy.bll_usercenter.repository;

import com.zy.bll_usercenter.callback.ResultCallback;
import com.zy.bll_usercenter.contract.UserCenterContract;
import com.zy.bll_usercenter.model.protocol.request.UserReq;
import com.zy.bll_usercenter.model.service.UCModel;
import com.zy.net.protocol.response.BaseEntity;

import io.reactivex.Observable;

/**
 * @author:zhangyue
 * @date:2020/9/9
 */
public class UCRepository extends UserCenterContract.UserCenterRepository {

    @Override
    public Observable<BaseEntity<UserReq>> login(UserReq userReq) {
        //处理多数据源
        return mModel.login(userReq);
    }

    @Override
    protected void createModel() {
        mModel=new UCModel();
    }
}
