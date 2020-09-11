package com.zy.bll_usercenter.presenter;

import com.zy.bll_usercenter.callback.ResultCallback;
import com.zy.bll_usercenter.contract.UserCenterContract;
import com.zy.bll_usercenter.repository.UCRepository;

/**
 * @author:zhangyue
 * @date:2020/9/9
 */
public class UCPresenter extends UserCenterContract.UserCenterPresenter {
    public UCPresenter(UserCenterContract.UserCenterView userCenterView) {
        super(userCenterView);
    }

    @Override
    public void login(String username, String pwd) {
        mRepository.login(username, pwd, new ResultCallback() {
            @Override
            public void Success(Object... objects) {
                if (mView.get()!=null){
                    mView.get().loginSuccess();
                }

            }

            @Override
            public void Failed(Throwable throwable) {
                if (mView.get()!=null){
                    mView.get().loginFailed(throwable.getMessage());
                }
            }
        });
    }

    @Override
    protected void createRepository() {
        mRepository=new UCRepository();
    }
}
