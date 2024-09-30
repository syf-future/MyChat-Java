package com.syf.chat.common.enums;

public enum EnumReturnStatus {
    SUCCESS("200", "成功"),
    ERROR("500", "系统异常"),
    BASE_ERROR("501", "数据库异常"),
    PARAM_ERROR("502", "参数异常"),
    USERINFO_ERROR("511", "账号或密码错误"),
    USER_NOT_EXIST("512", "用户不存在"),
    USER_EXIST("513", "用户已存在"),
    PHONE_ERROR("514", "手机号不存在");

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
