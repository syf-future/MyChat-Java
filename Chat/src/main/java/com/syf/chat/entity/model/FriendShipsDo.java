package com.syf.chat.entity.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 好友关系表
 */
@Data
@TableName("FRIEND_SHIPS")
public class FriendShipsDo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
    @TableId
    private String serialNo;
    /**
     * 发起用户号
     */
    private String userId;
    /**
     * 好友用户号
     */
    private String friendId;
    /**
     * 群组id (好友时为空)
     */
    private String groupId;
    /**
     * 发送类型 FRIEND好友  GROUP群聊
     */
    private String sendType;
    /**
     * 好友关系 0发起请求 1同意 2拒绝  EnumAddFriendState
     */
    private String status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

}
