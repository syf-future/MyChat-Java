package com.syf.chat.common.enums;

public enum EnumChatType {
    CHAT0("0","登录"),
    CHAT1("1","发消息给好友"),
    CHAT2("2","发消息给群");

    private String code;
    private String msg;

    EnumChatType(String code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    public String getCode(){
        return code;
    }

    public String getMsg(){
        return msg;
    }
}
