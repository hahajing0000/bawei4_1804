package com.zy.im.core;

import android.content.Context;

import com.zy.im.callback.ZCallback;
import com.zy.im.callback.ZConnectionListener;
import com.zy.im.core.chatroom.ZChatroom;

/**
 * @author:zhangyue
 * @date:2020/9/18
 */
public interface ZIM {
    /**
     * IM环境的初始化
     * @param context
     */
    void init(Context context);

    /**
     * 注册
     * @param username 用户名
     * @param pwd 密码
     * @param callback 结果回调
     */
    void register(String username, String pwd, ZCallback callback);

    /**
     * 登录
     * @param username 用户名
     * @param pwd 密码
     * @param callback 接口回调
     */
    void login(String username,String pwd,ZCallback callback);

    /**
     * 退出登录
     * @param callback
     */
    void logout(ZCallback callback);

    /**
     * 添加链接的监听
     * @param listener
     */
    void addConnectionListener(ZConnectionListener listener);

    /**
     * 获取聊天室的具体实例
     * @return
     */
    ZChatroom getChatRoomInstance();
}
