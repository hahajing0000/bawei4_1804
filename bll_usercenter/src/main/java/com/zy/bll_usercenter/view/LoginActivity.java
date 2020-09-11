package com.zy.bll_usercenter.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zy.bll_usercenter.R;
import com.zy.bll_usercenter.contract.UserCenterContract;
import com.zy.bll_usercenter.presenter.UCPresenter;
import com.zy.core.mvp.ui.BaseActivity;
import com.zy.widget.CircleView;
import com.zy.widget.MyProgress;

public class LoginActivity extends BaseActivity<UCPresenter> implements UserCenterContract.UserCenterView {
    private EditText etUsername;
    private EditText etPwd;
    private Button btnLogin;
    private MyProgress mpTest;
    private CircleView cvTest;





    @Override
    protected void initEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=etUsername.getText().toString();
                String pwd=etPwd.getText().toString();
                mPresenter.login(username,pwd);
            }
        });
    }

    @Override
    protected void initData() {
        cvTest.startAnimal();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        etUsername = (EditText) $(R.id.et_username);
        etPwd = (EditText) $(R.id.et_pwd);
        btnLogin = (Button) $(R.id.btn_login);
        cvTest = (CircleView) $(R.id.cv_test);
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