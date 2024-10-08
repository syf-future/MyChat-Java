package com.syf.chat.entity.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class AddFriendVo implements Serializable {
    @Serial  // 序列化  序列版本UID
    private static final long serialVersionUID = 1L;

    /**
     * 当前用户id
     */
    private String serialNo;
    /**
     * 要添加的好友/群组id
     */
    private String userId;
    /**
     * FRIEND 加好友
     * GROUP 加群
     */
    private String state;
}
