package com.syf.serverControl.common.enums;

public enum EnumReturnStatus {
    SUCCESS("200", "成功"),
    ERROR("500", "系统异常"),
    BASE_ERROR("501", "数据库异常"),
    PARAM_ERROR("502", "参数异常"),
    SERVER_ERROR("503", "服务器异常"),
    SERVER_UNUNITED("600", "服务器未连接");

    private final String code;
    private final String msg;

    EnumReturnStatus(String code, String msg) {
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
