package com.syf.chat.common.enums;

/**
 * 消息类型
 */
public enum EnumMessageType {
    TEXT("TEXT", "文本"),
    IMAGE("IMAGE", "图片"),
    FILE("FILE", "文件"),
    VIDEO("VIDEO", "视频"),
    AUDIO("AUDIO", "音频");

    private final String code;
    private final String msg;

    EnumMessageType(String code, String msg) {
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
