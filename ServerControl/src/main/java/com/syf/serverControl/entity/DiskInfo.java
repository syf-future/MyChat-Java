package com.syf.serverControl.entity;

import lombok.Data;

/**
 * 磁盘信息
 */
@Data
public class DiskInfo {
    /**
     * 磁盘总大小
     */
    private String diskTotal;
    /**
     * 磁盘已使用大小
     */
    private String diskUsed;
    /**
     * 磁盘剩余大小
     */
    private String diskFree;
    /**
     * 磁盘使用率
     */
    private String diskUsage;
}
