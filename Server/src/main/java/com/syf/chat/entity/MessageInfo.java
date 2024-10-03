package com.syf.chat.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
public class MessageInfo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 发送者ID
     */
    private String userId;
    /**
     * 接收者id (群聊时为空)
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
     * 消息类型  'text':文本, 'image':图片, 'file':文件, 'video':视频, 'audio':音频
     */
    private String messageType;
    /**
     * 内容
     */
    private String content;
}
