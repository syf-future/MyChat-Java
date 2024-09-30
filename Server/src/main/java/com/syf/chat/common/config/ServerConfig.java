package com.syf.chat.common.config;

/**
 * 服务端配置类
 */
public class ServerConfig {
    private String address = "0.0.0.0"; // 监听地址
    private Integer port = 1234;        // 监听端口
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
