package com.zy.bll_usercenter.presenter;

import com.zy.bll_usercenter.callback.ResultCallback;
import com.zy.bll_usercenter.contract.UserCenterContract;
import com.zy.bll_usercenter.model.protocol.request.UserReq;
import com.zy.bll_usercenter.repository.UCRepository;
import com.zy.net.protocol.response.BaseEntity;
import com.zy.net.rxjava.BaseObservable;
import com.zy.net.rxjava.BaseObserver;

/**
 * @author:zhangyue
 * @date:2020/9/9
 */
public class UCPresenter extends UserCenterContract.UserCenterPresenter {
    public UCPresenter(UserCenterContract.UserCenterView userCenterView) {
        super(userCenterView);
    }

    @Override
    public void login(UserReq userReq) {
        BaseObservable.doObservable(mRepository.login(userReq), new BaseObserver<BaseEntity<UserReq>>() {
            @Override
            public void onNext(BaseEntity<UserReq> baseEntity) {
                if (baseEntity.getCode()==-1){
                    if (mView.get()!=null){
                        mView.get().loginFailed(baseEntity.getMsg());
                    }
                }
                else {
                    if (mView.get()!=null){
                        mView.get().loginSuccess();
                    }
                }
            }

            @Override
            public void onError(Throwable error) {
                if (mView.get()!=null){
                    mView.get().loginFailed(error.getMessage());
                }
            }
        });
    }

    @Override
    protected void createRepository() {
        mRepository=new UCRepository();
    }
}
