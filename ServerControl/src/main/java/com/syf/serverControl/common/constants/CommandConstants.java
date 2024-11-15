package com.syf.serverControl.common.constants;

/**
 * linux 命令
 */
public class CommandConstants {
    /**
     * 服务器基本信息
     */
    public static final String UNAME = "uname -a";
    /**
     * 系统运行时间命令
     */
    public static final String UPTIME = "uptime";
    /**
     * 系统公网ip命令
     */
    public static final String CURL = "curl -s http://checkip.amazonaws.com";
    /**
     * cpu信息命令
     */
    public static final String CPU = "lscpu";
    /**
     * 内存信息命令
     */
    public static final String FREE = "free -h";
    /**
     * 磁盘信息命令
     */
    public static final String  DF = "df -h";
}
