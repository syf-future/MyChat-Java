package com.syf.chat.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public  class Message<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String messageId;   //消息id
    private String chatType;    //消息类型
    private T data;             //数据

}
