package com.zy.bawei4_1804;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zy.common.others.AsyncUtils;
import com.zy.common.others.MsgUtils;
import com.zy.im.ZIMManager;
import com.zy.im.callback.ZCallback;

public class IMTestActivity extends AppCompatActivity {
    private EditText etUsername;
    private EditText etPwd;
    private Button btnRegister;
    private Button btnLogin;
    private Button btnLogout;

    private EditText etChatroomName;
    private EditText etChatroomDesc;
    private Button btnCreateChatroom;
    private Button btnJoinChatroom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im_test);

        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=etUsername.getText().toString().trim();
                String pwd=etPwd.getText().toString().trim();

                AsyncUtils.getInstance().doTaskByCachePool(new Runnable() {
                    @Override
                    public void run() {
                        ZIMManager.getInstance().register(username, pwd, new ZCallback() {
                            @Override
                            public void onSuccess(Object object) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        MsgUtils.showMsg(IMTestActivity.this,"注册成功");
                                    }
                                });
                            }

                            @Override
                            public void onProgress(int progress, String status) {

                            }

                            @Override
                            public void onError(int code, String message) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        MsgUtils.showMsg(IMTestActivity.this,"注册失败，Code:"+code);
                                    }
                                });
                            }
                        });
                    }
                });

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=etUsername.getText().toString().trim();
                String pwd=etPwd.getText().toString().trim();
                ZIMManager.getInstance().login(username, pwd, new ZCallback() {
                    @Override
                    public void onSuccess(Object object) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                MsgUtils.showMsg(IMTestActivity.this,"登录成功");
                            }
                        });

                    }

                    @Override
                    public void onProgress(int progress, String status) {

                    }

                    @Override
                    public void onError(int code, String message) {
                       runOnUiThread(new Runnable() {
                           @Override
                           public void run() {
                               MsgUtils.showMsg(IMTestActivity.this,"登录失败 Code:"+code);
                           }
                       });
                    }
                });
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnCreateChatroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=etChatroomName.getText().toString().trim();
                String desc=etChatroomDesc.getText().toString().trim();
                AsyncUtils.getInstance().doTaskByCachePool(new Runnable() {
                    @Override
                    public void run() {
                        ZIMManager.getInstance().getChatRoomInstance().createChatroom(name, desc, "Welcome", 500, new ZCallback() {
                            @Override
                            public void onSuccess(Object o) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        MsgUtils.showMsg(IMTestActivity.this,"创建聊天成功");
                                    }
                                });
                            }

                            @Override
                            public void onProgress(int progress, String status) {

                            }

                            @Override
                            public void onError(int code, String message) {
                               runOnUiThread(new Runnable() {
                                   @Override
                                   public void run() {
                                       MsgUtils.showMsg(IMTestActivity.this,"创建聊天失败");
                                   }
                               });
                            }
                        });
                    }
                });
            }
        });
    }

    private void initData() {
    }

    private void initView() {

        etUsername = (EditText) findViewById(R.id.et_username);
        etPwd = (EditText) findViewById(R.id.et_pwd);
        btnRegister = (Button) findViewById(R.id.btn_register);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogout = (Button) findViewById(R.id.btn_logout);


        etChatroomName = (EditText) findViewById(R.id.et_chatroom_name);
        etChatroomDesc = (EditText) findViewById(R.id.et_chatroom_desc);
        btnCreateChatroom = (Button) findViewById(R.id.btn_create_chatroom);
        btnJoinChatroom = (Button) findViewById(R.id.btn_join_chatroom);
    }
}