package com.zy.bll_usercenter.contract;

import com.zy.bll_usercenter.callback.ResultCallback;
import com.zy.bll_usercenter.model.protocol.request.UserReq;
import com.zy.core.mvp.IModel;
import com.zy.core.mvp.IView;
import com.zy.core.mvp.Presenter;
import com.zy.core.mvp.Repository;
import com.zy.net.protocol.response.BaseEntity;

import io.reactivex.Observable;

/**
 * @author:zhangyue
 * @date:2020/9/9
 */
public interface UserCenterContract {

    interface UserCenterView extends IView{
        void loginSuccess();
        void loginFailed(String error);
    }

    interface UserCenterModel extends IModel {
        Observable<BaseEntity<UserReq>> login(UserReq userReq);
    }

    abstract class UserCenterRepository extends Repository<UserCenterModel>{
        public abstract Observable<BaseEntity<UserReq>> login(UserReq userReq);
    }

    abstract class UserCenterPresenter extends Presenter<UserCenterRepository,UserCenterView>{

        public UserCenterPresenter(UserCenterView userCenterView) {
            super(userCenterView);
        }

        public abstract void login(UserReq userReq);
    }
}
