package com.zy.im.entity;

import java.util.List;

/**
 * @author:zhangyue
 * @date:2020/9/18
 */
public class ChatRoomInfo {
    private int memberCount;
    private List<String> members;

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }
}
