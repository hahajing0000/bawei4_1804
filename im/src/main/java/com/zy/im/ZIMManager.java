package com.zy.im;

import android.content.Context;

import com.zy.im.callback.ZCallback;
import com.zy.im.callback.ZConnectionListener;
import com.zy.im.core.HuanXinIM;
import com.zy.im.core.ZIM;
import com.zy.im.core.chatroom.ZChatroom;

/**
 * @author:zhangyue
 * @date:2020/9/18
 */
public class ZIMManager {
    private ZIM im;
    private static ZIMManager instance=new ZIMManager();
    private ZIMManager(){
        im=new HuanXinIM();
    }
    public static ZIMManager getInstance(){
        return instance;
    }

    /**
     * 设置IM端（网易云信 友盟IM 。。。）
     * @param im
     */
    public void setIm(ZIM im) {
        this.im = im;
    }

    /**
     * IM环境的初始化
     * @param context
     */
    public void init(Context context){
        im.init(context);
    }

    /**
     * 注册
     * @param username 用户名
     * @param pwd 密码
     * @param callback 结果回调
     */
    public void register(String username, String pwd, ZCallback callback){
        im.register(username,pwd,callback);
    }

    /**
     * 登录
     * @param username 用户名
     * @param pwd 密码
     * @param callback 接口回调
     */
    public void login(String username,String pwd,ZCallback callback){
        im.login(username,pwd,callback);
    }

    /**
     * 退出登录
     * @param callback
     */
    public void logout(ZCallback callback){
        im.logout(callback);
    }

    /**
     * 添加链接的监听
     * @param listener
     */
    public void addConnectionListener(ZConnectionListener listener){
        im.addConnectionListener(listener);
    }

    /**
     * 获取聊天室的具体实例
     * @return
     */
    public ZChatroom getChatRoomInstance(){
        return im.getChatRoomInstance();
    }
}
