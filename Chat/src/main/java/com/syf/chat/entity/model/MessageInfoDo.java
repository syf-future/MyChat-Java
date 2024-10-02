package com.syf.chat.entity.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 消息信息表
 */
@Data
@TableName("MESSAGE_INFO")
public class MessageInfoDo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 流水号
     */
    @TableId
    private String serialNo;
    /**
     * 发送者ID
     */
    private String UserId;
    /**
     * 接收者id (群聊时为空)
     */
    private String FriendId;
    /**
     * 群组id (好友时为空)
     */
    private String GroupId;
    /**
     * 发送类型 FRIEND好友  GROUP群聊
     */
    private String SendType;
    /**
     * 消息类型  'text':文本, 'image':图片, 'file':文件, 'video':视频, 'audio':音频
     */
    private String MessageType;
    /**
     * 内容
     */
    private String content;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

}