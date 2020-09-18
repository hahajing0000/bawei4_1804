package com.zy.im.core.chatroom;

import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMChatRoom;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.zy.im.callback.ZCallback;
import com.zy.im.common.ErrorCode;
import com.zy.im.entity.ChatRoomInfo;

import java.util.ArrayList;

/**
 * @author:zhangyue
 * @date:2020/9/18
 */
public class HuanXinChatroom implements ZChatroom {
    @Override
    public void addChatroom(String roomid, ZCallback callback) {
        //roomId为聊天室ID
        EMClient.getInstance().chatroomManager().joinChatRoom(roomid, new EMValueCallBack<EMChatRoom>() {

            @Override
            public void onSuccess(EMChatRoom value) {
                ChatRoomInfo info=convertFromEMChatRoom(value);
                //加入聊天室成功
                callback.onSuccess(info);
            }


            @Override
            public void onError(final int error, String errorMsg) {
                //加入聊天室失败
                callback.onError(error,errorMsg);
            }
        });
    }

    @Override
    public void leaveChatroom(String toChatUsername) {
        EMClient.getInstance().chatroomManager().leaveChatRoom(toChatUsername);
    }

    @Override
    public void createChatroom(String chatroomname, String description, String welcomeMsg, int maxusercount,ZCallback callback) {
        try {
            EMChatRoom chatRoom = EMClient.getInstance().chatroomManager().createChatRoom(chatroomname, description, welcomeMsg, maxusercount, null);
            callback.onSuccess(chatRoom.getId());
        } catch (HyphenateException e) {
            callback.onError(ErrorCode.NormalErrorCode,e.getMessage());
        }
    }

    @Override
    public void destroyChatroom(String chatroomid,ZCallback callback) {
        try {
            EMClient.getInstance().chatroomManager().destroyChatRoom(chatroomid);
            callback.onSuccess(null);
        } catch (HyphenateException e) {
            callback.onError(ErrorCode.NormalErrorCode,e.getMessage());
        }
    }

    private ChatRoomInfo convertFromEMChatRoom(EMChatRoom value) {
        ChatRoomInfo info=new ChatRoomInfo();
        info.setMemberCount(value.getMemberCount());
        info.setMembers(value.getMemberList());
        return info;
    }

}
