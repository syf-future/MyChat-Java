package com.syf.serverControl.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class MemoryInfo implements Serializable {
    /**
     * 总内存
     */
    private String totalMemory;
    /**
     * 已使用内存
     */
    private String usedMemory;
    /**
     * 当前空闲内存
     */
    private String freeMemory;
    /**
     * 可用内存
     */
    private String availableMemory;
}
