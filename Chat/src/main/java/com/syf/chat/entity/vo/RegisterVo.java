package com.syf.chat.entity.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户注册请求参数
 */
@Data
public class RegisterVo implements Serializable {
    @Serial  // 序列化  序列版本UID
    private static final long serialVersionUID = 1L;

    private String account;
    private String password;
    private String phone;
}
