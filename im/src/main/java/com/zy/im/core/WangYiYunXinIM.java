package com.zy.im.core;

import android.content.Context;

import com.zy.im.callback.ZCallback;
import com.zy.im.callback.ZConnectionListener;
import com.zy.im.core.chatroom.ZChatroom;

/**
 * @author:zhangyue
 * @date:2020/9/18
 */
public class WangYiYunXinIM implements ZIM {
    @Override
    public void init(Context context) {

    }

    @Override
    public void register(String username, String pwd, ZCallback callback) {

    }

    @Override
    public void login(String username, String pwd, ZCallback callback) {

    }

    @Override
    public void logout(ZCallback callback) {

    }

    @Override
    public void addConnectionListener(ZConnectionListener listener) {

    }

    @Override
    public ZChatroom getChatRoomInstance() {
        return null;
    }
}
