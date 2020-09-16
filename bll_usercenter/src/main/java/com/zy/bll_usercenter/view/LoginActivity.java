package com.zy.bll_usercenter.view;

import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zy.bll_usercenter.MyApplication;
import com.zy.bll_usercenter.R;
import com.zy.bll_usercenter.contract.UserCenterContract;
import com.zy.bll_usercenter.greendao.DaoSession;
import com.zy.bll_usercenter.greendao.UserEntity;
import com.zy.bll_usercenter.greendao.UserEntityDao;
import com.zy.bll_usercenter.model.protocol.request.UserReq;
import com.zy.bll_usercenter.presenter.UCPresenter;
import com.zy.common.log.LogUtils;
import com.zy.core.mvp.ui.BaseActivity;
import com.zy.imageloader.ImageLoaderManager;
import com.zy.net.RetrofitFactory;
import com.zy.storage.callback.ResultCallback;
import com.zy.storage.chain.StorageChainManager;
import com.zy.widget.CircleView;
import com.zy.widget.MyProgress;

import java.util.LinkedHashMap;
import java.util.Map;

public class LoginActivity extends BaseActivity<UCPresenter> implements UserCenterContract.UserCenterView {
    private EditText etUsername;
    private EditText etPwd;
    private Button btnLogin;
    private Button btnTestGreendao;




    @Override
    protected void initEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=etUsername.getText().toString();
                String pwd=etPwd.getText().toString();
                mPresenter.login(new UserReq(username,pwd));
            }
        });


        btnTestGreendao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                UserEntity userEntity=new UserEntity();
                userEntity.setAge(20);
                userEntity.setUsername("小明");

                StorageChainManager.getInstance().putValue(userEntity.getClass().getSimpleName(),userEntity);


                StorageChainManager.getInstance().getValue(userEntity.getClass().getSimpleName(), new ResultCallback() {
                    @Override
                    public void Success(Object o) {
                        UserEntity userEntity1= (UserEntity) o;
                        LogUtils.getInstance().i(userEntity1.toString());
                    }
                });

            }
        });
    }

    UserEntityDao userDao;
    @Override
    protected void initData() {
        DaoSession daoSession = ((MyApplication) getApplication()).getDaoSession();
        userDao = daoSession.getUserEntityDao();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        etUsername = (EditText) $(R.id.et_username);
        etPwd = (EditText) $(R.id.et_pwd);
        btnLogin = (Button) $(R.id.btn_login);


        btnTestGreendao = (Button) findViewById(R.id.btn_test_greendao);


        ImageLoaderManager.getInstance().load(this,"",null);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void createPresenter() {
        mPresenter=new UCPresenter(this);
    }

    @Override
    public void loginSuccess() {
        showMsg("登录成功");
    }

    @Override
    public void loginFailed(String error) {
        showMsg("登录失败，INFO："+error);
    }

}