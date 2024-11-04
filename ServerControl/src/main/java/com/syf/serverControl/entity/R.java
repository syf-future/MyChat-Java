package com.syf.serverControl.entity;

import com.syf.serverControl.common.enums.EnumReturnStatus;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class R<T> implements Serializable {
    @Serial  // 序列化  序列版本UID
    private static final long serialVersionUID = 1L;
    private String code;
    private String msg;
    private T data;

    public R(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public static <T> R<T> ok() {
        return new R<T>(EnumReturnStatus.SUCCESS.getCode(), EnumReturnStatus.SUCCESS.getMsg(), null);
    }

    public static <T> R<T> ok(T data) {
        return new R<T>(EnumReturnStatus.SUCCESS.getCode(), EnumReturnStatus.SUCCESS.getMsg(), data);
    }

    public static <T> R<T> fail() {
        return new R<T>(EnumReturnStatus.ERROR.getCode(), EnumReturnStatus.ERROR.getMsg(), null);
    }

    public static <T> R<T> fail(EnumReturnStatus enumReturnStatus) {
        return new R<T>(enumReturnStatus.getCode(), enumReturnStatus.getMsg(), null);
    }

    public static <T> R<T> fail(EnumReturnStatus enumReturnStatus, T data) {
        return new R<T>(enumReturnStatus.getCode(), enumReturnStatus.getMsg(), data);
    }

    public static <T> R<T> fail(String code, String msg, T data) {
        return new R<T>(code, msg, data);
    }
}
