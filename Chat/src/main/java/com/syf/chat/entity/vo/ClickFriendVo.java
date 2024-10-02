package com.syf.chat.entity.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class ClickFriendVo implements Serializable {
    @Serial  // 序列化  序列版本UID
    private static final long serialVersionUID = 1L;

    /**
     * 发送者ID
     */
    private String UserId;
    /**
     * 接收者id (群聊时为空)
     */
    private String FriendId;
}
