package com.syf.serverControl.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class CPUInfo implements Serializable {
    /**
     * CPU 型号
     */
    private String cpuModel;
    /**
     * CPU 架构类型
     */
    private String cpuType;
    /**
     * CPU 总核心数
     */
    private String cpuNum;
    /**
     * CPU 每个核心的线程数
     */
    private String cpuThreadNum;
}
