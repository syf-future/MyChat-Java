package com.syf.chat.entity.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
public class MessageInfoDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 消息发送者用户id
     */
    private String userId;
    /**
     * 消息类型  'text':文本, 'image':图片, 'file':文件, 'video':视频, 'audio':音频
     */
    private String MessageType;
    /**
     * 内容
     */
    private String content;
    /**
     * 消息发送时间
     */
    private Date sendDate;
}
