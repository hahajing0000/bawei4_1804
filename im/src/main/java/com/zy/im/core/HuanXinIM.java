package com.zy.im.core;

import android.content.Context;
import android.os.Looper;

import com.hyphenate.EMCallBack;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.exceptions.HyphenateException;
import com.zy.common.info.AppUtils;
import com.zy.common.log.LogUtils;
import com.zy.im.callback.ZCallback;
import com.zy.im.callback.ZConnectionListener;
import com.zy.im.common.ErrorCode;
import com.zy.im.core.chatroom.HuanXinChatroom;
import com.zy.im.core.chatroom.ZChatroom;

/**
 * @author:zhangyue
 * @date:2020/9/18
 */
public class HuanXinIM implements ZIM {
    @Override
    public void init(Context context) {
        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        // 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
        options.setAutoTransferMessageAttachments(true);
        // 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
        options.setAutoDownloadThumbnail(true);

        //关闭自动登录
//        options.setAutoLogin(false);

        int pid = android.os.Process.myPid();
        String processAppName = AppUtils.getInstance().getAppName(context,pid);
        // 如果APP启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回

        if (processAppName == null ||!processAppName.equalsIgnoreCase(context.getPackageName())) {
            LogUtils.getInstance().e( "enter the service process!");

            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }


        //初始化
        EMClient.getInstance().init(context, options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
    }

    @Override
    public void register(String username, String pwd, ZCallback callback) {
        try {
            EMClient.getInstance().createAccount(username, pwd);//同步方法
            callback.onSuccess(null);
        } catch (HyphenateException e) {
            callback.onError(ErrorCode.NormalErrorCode,e.getMessage());
        }
    }

    @Override
    public void login(String username, String pwd, ZCallback callback) {
        EMClient.getInstance().login(username,pwd,new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                LogUtils.getInstance().d("main", "登录聊天服务器成功！");
                callback.onSuccess(null);
            }

            @Override
            public void onProgress(int progress, String status) {
                callback.onProgress(progress,status);
            }

            @Override
            public void onError(int code, String message) {
                LogUtils.getInstance().d("main", "登录聊天服务器失败！");
                callback.onError(code,message);
            }
        });
    }

    @Override
    public void logout(ZCallback callback) {
        EMClient.getInstance().logout(true, new EMCallBack() {

            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                callback.onSuccess(null);
            }

            @Override
            public void onProgress(int progress, String status) {
                // TODO Auto-generated method stub
                callback.onProgress(progress,status);
            }

            @Override
            public void onError(int code, String message) {
                // TODO Auto-generated method stub
                callback.onError(code,message);
            }
        });
    }

    @Override
    public void addConnectionListener(ZConnectionListener listener) {
        //注册一个监听连接状态的listener
        EMClient.getInstance().addConnectionListener(new EMConnectionListener() {
            @Override
            public void onConnected() {

                listener.onConnected();
            }

            @Override
            public void onDisconnected(int error) {
                if (error == EMError.USER_REMOVED) {
                } else if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                } else if (error == EMError.SERVER_SERVICE_RESTRICTED) {
                } else if (error == EMError.USER_KICKED_BY_CHANGE_PASSWORD) {
                } else if (error == EMError.USER_KICKED_BY_OTHER_DEVICE) {
                }
                listener.onDisconnected(error);
            }
        });

    }

    @Override
    public ZChatroom getChatRoomInstance() {
        return new HuanXinChatroom();
    }
}
