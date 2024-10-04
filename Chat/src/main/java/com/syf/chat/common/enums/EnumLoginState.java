package com.syf.chat.common.enums;

public enum EnumLoginState {
    LOGIN("LOGIN", "登录"),
    UN_LOGIN("UN_LOGIN", "未登录");
    private final String code;
    private final String msg;

    EnumLoginState(String code, String msg) {
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
