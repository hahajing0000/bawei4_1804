package com.zy.bll_usercenter.model.service;

import android.os.SystemClock;

import com.zy.bll_usercenter.callback.ResultCallback;
import com.zy.bll_usercenter.contract.UserCenterContract;
import com.zy.bll_usercenter.model.api.UserCenterApi;
import com.zy.bll_usercenter.model.protocol.request.UserReq;
import com.zy.net.RetrofitFactory;
import com.zy.net.protocol.response.BaseEntity;
import com.zy.net.rxjava.BaseObservable;
import com.zy.net.rxjava.BaseObserver;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author:zhangyue
 * @date:2020/9/9
 */
public class UCModel implements UserCenterContract.UserCenterModel {
    @Override
    public Observable<BaseEntity<UserReq>> login(UserReq userReq) {
        //模拟一个请求 Retrofit请求的位置
        UserCenterApi userCenterApi = RetrofitFactory.getInstance().create(UserCenterApi.class);

        return  userCenterApi.login(userReq);

    }

    @Override
    public void destory() {

    }
}
