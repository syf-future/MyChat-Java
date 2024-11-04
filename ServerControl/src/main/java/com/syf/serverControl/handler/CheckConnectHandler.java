package com.syf.serverControl.handler;

import com.syf.serverControl.common.enums.EnumConnectStatus;
import com.syf.serverControl.entity.CPUInfo;
import com.syf.serverControl.entity.DiskInfo;
import com.syf.serverControl.entity.MemoryInfo;
import com.syf.serverControl.entity.ServerInfo;

import java.util.Objects;

/**
 * 获取服务器实例
 */
public class CheckConnectHandler {
    /**
     * 连接状态
     */
    private EnumConnectStatus enumConnectStatus;
    /**
     * 服务器信息
     */
    private ServerInfo serverInfo;
    /**
     * CPU信息
     */
    private CPUInfo cpuInfo;
    /**
     * 内存信息
     */
    private MemoryInfo memoryInfo;
    /**
     * 磁盘信息
     */
    private DiskInfo diskInfo;

    private static volatile CheckConnectHandler checkConnectHandler;

    public static CheckConnectHandler CheckConnect() {
        if (Objects.isNull(checkConnectHandler)) {
            synchronized (CheckConnectHandler.class) {
                if (Objects.isNull(checkConnectHandler)) {
                    checkConnectHandler = new CheckConnectHandler();
                }
            }
        }
        return checkConnectHandler;
    }

    public static boolean checkBasicsInfo() {
        if (Objects.isNull(CheckConnectHandler.CheckConnect().getCpuInfo())) {
            return false;
        }
        if (Objects.isNull(CheckConnectHandler.CheckConnect().getMemoryInfo())) {
            return false;
        }
        if (Objects.isNull(CheckConnectHandler.CheckConnect().getDiskInfo())) {
            return false;
        }
        if (Objects.isNull(CheckConnectHandler.CheckConnect().getServerInfo())) {
            return false;
        }
        return true;
    }


    public EnumConnectStatus getEnumConnectStatus() {
        return enumConnectStatus;
    }

    public void setEnumConnectStatus(EnumConnectStatus enumConnectStatus) {
        this.enumConnectStatus = enumConnectStatus;
    }

    public CPUInfo getCpuInfo() {
        return cpuInfo;
    }

    public void setCpuInfo(CPUInfo cpuInfo) {
        this.cpuInfo = cpuInfo;
    }

    public MemoryInfo getMemoryInfo() {
        return memoryInfo;
    }

    public void setMemoryInfo(MemoryInfo memoryInfo) {
        this.memoryInfo = memoryInfo;
    }

    public ServerInfo getServerInfo() {
        return serverInfo;
    }

    public void setServerInfo(ServerInfo serverInfo) {
        this.serverInfo = serverInfo;
    }

    public DiskInfo getDiskInfo() {
        return diskInfo;
    }

    public void setDiskInfo(DiskInfo diskInfo) {
        this.diskInfo = diskInfo;
    }
}
