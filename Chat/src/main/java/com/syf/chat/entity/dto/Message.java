package com.syf.chat.entity.dto;

import com.syf.chat.entity.model.MessageInfoDo;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public  class Message implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String messageId;   //消息id
    private String chatType;    //消息类型
    private MessageInfoDo data; //数据
}
