package com.syf.serverControl.entity.dto;

import com.syf.serverControl.entity.CPUInfo;
import com.syf.serverControl.entity.DiskInfo;
import com.syf.serverControl.entity.MemoryInfo;
import com.syf.serverControl.entity.ServerInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * 服务器信息Dto
 */
@Data
public class ServerInfoDto implements Serializable {
    /**
     * 服务器信息
     */
    private ServerInfo serverInfo;
    /**
     * cpu信息
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
}
