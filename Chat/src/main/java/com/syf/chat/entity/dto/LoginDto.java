package com.syf.chat.entity.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class LoginDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
    private String serialNo;
    /**
     * 账号
     */
    private String account;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 头像
     */
    private String picture;

}