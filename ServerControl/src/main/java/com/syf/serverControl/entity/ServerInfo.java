package com.syf.serverControl.entity;

import lombok.Data;

import java.io.Serializable;


@Data
public class ServerInfo implements Serializable {
    /**
     * 服务器ip
     */
    private String host;
    /**
     * 服务器名称
     */
    private String serverName;
    /**
     * 操作系统名称
     */
    private String osName;
    /**
     * 系统运行时间
     */
    private String runTime;
    /**
     * 当前连接数量
     */
    private String connectNum;
}
