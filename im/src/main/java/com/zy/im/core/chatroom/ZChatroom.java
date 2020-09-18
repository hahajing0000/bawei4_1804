package com.zy.im.core.chatroom;

import com.zy.im.callback.ZCallback;

/**
 * @author:zhangyue
 * @date:2020/9/18
 */
public interface ZChatroom {
    /**
     * 加入聊天室
     * @param roomid
     * @param callback
     */
    void addChatroom(String roomid, ZCallback callback);

    /**
     * 离开聊天室
     * @param toChatUsername
     */
    void leaveChatroom(String toChatUsername);

    /**
     * 创建聊天室
     * @param chatroomname 聊天室名称
     * @param description 描述
     * @param welcomeMsg 欢迎语
     * @param maxusercount 最大人数
     */
    void createChatroom(String chatroomname,String description,String welcomeMsg,int maxusercount,ZCallback callback);

    /**
     * 销毁聊天室
     * @param chatroomid
     */
    void destroyChatroom(String chatroomid,ZCallback callback);
}
