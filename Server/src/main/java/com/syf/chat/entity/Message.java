package com.syf.chat.entity;

import com.syf.chat.common.enums.EnumSendType;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public  class Message<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String messageId;   //消息id
    private String sendType;    //消息类型
    private T data;             //数据

}
