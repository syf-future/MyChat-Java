package com.syf.serverControl.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 服务器连接类
 */
@Data
public class ServerInfoVo implements Serializable {
    /**
     * 服务器地址
     */
    private String host;
    /**
     * 端口号
     */
    private Integer port;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
}
