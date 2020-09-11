package com.zy.bll_usercenter.contract;

import com.zy.bll_usercenter.callback.ResultCallback;
import com.zy.core.mvp.IModel;
import com.zy.core.mvp.IView;
import com.zy.core.mvp.Presenter;
import com.zy.core.mvp.Repository;

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
        void login(String username, String pwd, ResultCallback callback);
    }

    abstract class UserCenterRepository extends Repository<UserCenterModel>{
        public abstract void login(String username,String pwd,ResultCallback callback);
    }

    abstract class UserCenterPresenter extends Presenter<UserCenterRepository,UserCenterView>{

        public UserCenterPresenter(UserCenterView userCenterView) {
            super(userCenterView);
        }

        public abstract void login(String username,String pwd);
    }
}
