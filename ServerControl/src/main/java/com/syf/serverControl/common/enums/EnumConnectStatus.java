package com.syf.serverControl.common.enums;

public enum EnumConnectStatus {
    CONNECTED("connected", "已连接"),
    DISCONNECT("disconnect", "断开连接")
    ;

    private final String code;
    private final String msg;

    EnumConnectStatus(String code, String msg) {
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
