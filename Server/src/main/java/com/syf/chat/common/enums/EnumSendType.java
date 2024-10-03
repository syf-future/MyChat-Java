package com.syf.chat.common.enums;

/**
 * 消息发送类型
 */
public enum EnumSendType {
    LOGIN("LOGIN", "登录"),
    FRIEND("FRIEND", "好友"),
    GROUP("GROUP", "群组");

    private final String code;
    private final String msg;

    EnumSendType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
