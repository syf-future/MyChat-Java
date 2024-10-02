package com.syf.chat.entity.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
public class FriendInfoDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 好友流水号
     */
    private String FriendId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 头像
     */
    private String picture;
    /**
     * 最后一条消息
     */
    private String lastMessage;
    /**
     * 最后一天消息时间
     */
    private Date lastMessageTime;
}
