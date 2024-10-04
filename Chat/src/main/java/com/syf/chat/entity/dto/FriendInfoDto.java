package com.syf.chat.entity.dto;

import com.syf.chat.common.enums.EnumSendType;
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
    private String friendId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 头像
     */
    private String picture;
    /**
     * 发送的类型  FRIEND:好友  GROUP:群组
     */
    private String sendType = EnumSendType.FRIEND.getCode();
    /**
     * 最后一条消息
     */
    private String lastMessage;
    /**
     * 最后一天消息时间
     */
    private Date lastMessageTime;
    /**
     * 未读的消息数量
     */
    private String unreadNum;
}
