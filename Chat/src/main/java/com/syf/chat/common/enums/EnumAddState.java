package com.syf.chat.common.enums;

/**
 * 加好友/群组状态
 */
public enum EnumAddState {
    PASS("0", "发起请求"),
    SUCCESS("1", "通过"),
    REFUSE("2", "拒绝");

    private final String code;
    private final String msg;

    EnumAddState(String code, String msg) {
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
