package com.syf.chat.common.enums;

public enum EnumChatState {
    CHAT0("0","注册"),
    CHAT1("1","发消息"),
    CHAT2("2","加好友");

    private String code;
    private String msg;

    EnumChatState(String code,String msg)
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
