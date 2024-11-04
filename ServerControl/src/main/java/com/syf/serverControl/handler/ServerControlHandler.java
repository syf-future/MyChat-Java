package com.syf.serverControl.handler;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.syf.serverControl.common.constants.CommandConstants;
import com.syf.serverControl.common.enums.EnumConnectStatus;
import com.syf.serverControl.entity.CPUInfo;
import com.syf.serverControl.entity.DiskInfo;
import com.syf.serverControl.entity.MemoryInfo;
import com.syf.serverControl.entity.ServerInfo;
import com.syf.serverControl.entity.vo.ServerInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 服务器控制类
 */
@Slf4j
@Component
public class ServerControlHandler extends ServerBasicHandler {
    @Override
    public void startConnect(ServerInfoVo serverInfoVo) {
        log.info("开始连接服务器");
        JSch jSch = new JSch();
        try {
            super.session = jSch.getSession(serverInfoVo.getUserName(),
                    serverInfoVo.getHost(),
                    serverInfoVo.getPort());
            super.session.setPassword(serverInfoVo.getPassword());
            // 禁用主机密钥检查
            session.setConfig("StrictHostKeyChecking", "no");
            // 设置心跳间隔，单位为毫秒（如设置5分钟）
            super.session.setServerAliveInterval(300000);
            // 连接超时时间
            super.session.connect(10000);
            log.info("连接成功");
        } catch (JSchException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void closeConnect() {
        if (Objects.nonNull(super.session)) {
            if (super.session.isConnected()) {
                super.session.disconnect();
                log.info("断开服务器连接");
            }
        }
    }

    @Override
    public boolean checkConnect() {
        log.info("开始检查连接状态");
        CheckConnectHandler checkConnectHandler = CheckConnectHandler.CheckConnect();
        if (EnumConnectStatus.CONNECTED.equals(checkConnectHandler.getEnumConnectStatus())) {
            log.info("服务器已连接");
            return true;
        }
        log.info("服务器未连接");
        return false;
    }

    /**
     * 获取服务器基本信息
     */
    public void getEssentialInfo() {
        CheckConnectHandler checkConnectHandler = CheckConnectHandler.CheckConnect();
        //获取服务器系统名称
        String uname = super.executeCommand(CommandConstants.UNAME);
        ServerInfo serverInfo = new ServerInfo();
        String[] unames = uname.split("\\s+");
        serverInfo.setOsName(unames[0]);
        serverInfo.setServerName(unames[1]);
        //获取服务器ip
        String ip = super.executeCommand(CommandConstants.CURL);
        serverInfo.setHost(ip.trim());
        //获取服务器运行时间
        String uptime = super.executeCommand(CommandConstants.UPTIME).replace(",", "");
        String[] uptimes = uptime.split("\\s+");
        String runTime = uptimes[3] + "天" + uptimes[5].split(":")[0] + "小时" + uptimes[5].split(":")[1] + "分钟";
        serverInfo.setRunTime(runTime);
        serverInfo.setConnectNum(uptimes[6]);
        checkConnectHandler.setServerInfo(serverInfo);
        log.info("----服务器基本信息:{}", serverInfo);
        //获取服务器cpu信息
        String cpu = super.executeCommand(CommandConstants.CPU);
        String[] cpus = cpu.split("\n");
        CPUInfo cpuInfo = new CPUInfo();
        cpuInfo.setCpuType(cpus[0].split(":")[1].trim());
        cpuInfo.setCpuNum(cpus[3].split(":")[1].trim());
        cpuInfo.setCpuThreadNum(cpus[5].split(":")[1].trim());
        cpuInfo.setCpuModel(cpus[12].split(":")[1].trim());
        checkConnectHandler.setCpuInfo(cpuInfo);
        log.info("---cpu信息:{}", cpuInfo);
        //获取内存信息
        String free = super.executeCommand(CommandConstants.FREE);
        String[] frees = free.split("\n")[1].split("\\s+");
        MemoryInfo memoryInfo = new MemoryInfo();
        memoryInfo.setTotalMemory(frees[1]);
        memoryInfo.setUsedMemory(frees[2]);
        memoryInfo.setFreeMemory(frees[3]);
        memoryInfo.setAvailableMemory(frees[6]);
        checkConnectHandler.setMemoryInfo(memoryInfo);
        log.info("---内存信息：{}", memoryInfo);
        //获取磁盘信息
        String disk = super.executeCommand(CommandConstants.DF);
        String[] disks = disk.split("\n");
        DiskInfo diskInfo = new DiskInfo();
        for (String d : disks) {
            String[] split = d.split("\\s+");
            if (split[split.length - 1].equals("/")) {
                diskInfo.setDiskTotal(split[1]);
                diskInfo.setDiskUsed(split[2]);
                diskInfo.setDiskFree(split[3]);
                diskInfo.setDiskUsage(split[4]);
            }
        }
        checkConnectHandler.setDiskInfo(diskInfo);
        log.info("---磁盘信息：{}", diskInfo);
    }


}
