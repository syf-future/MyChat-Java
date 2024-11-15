package com.syf.serverControl.server.impl;

import com.syf.serverControl.common.enums.EnumConnectStatus;
import com.syf.serverControl.entity.R;
import com.syf.serverControl.entity.dto.ServerInfoDto;
import com.syf.serverControl.entity.vo.ServerInfoVo;
import com.syf.serverControl.handler.CheckConnectHandler;
import com.syf.serverControl.handler.ServerControlHandler;
import com.syf.serverControl.server.ConnectServer;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConnectServerImpl implements ConnectServer {
    @Resource
    private ServerControlHandler serverControlHandler;

    /**
     * 连接服务器
     */
    @Override
    public R<Object> connectServer(ServerInfoVo serverInfoVo) {
        if (serverControlHandler.checkConnect()) {
            log.info("服务器已连接");
            return R.ok();
        }
        // 当JVM准备关闭时 关闭服务器连接
        Runtime.getRuntime().addShutdownHook(new Thread(serverControlHandler::closeConnect));
        serverControlHandler.startConnect(serverInfoVo);
        CheckConnectHandler.CheckConnect().setEnumConnectStatus(EnumConnectStatus.CONNECTED);
        return R.ok();
    }

    /**
     * 断开服务器
     */
    @Override
    public R<Object> disconnect() {
        this.serverControlHandler.closeConnect();
        return R.ok();
    }

    /**
     * 获取服务器基本信息
     *
     * @return
     */
    @Override
    public R<Object> getEssentialInfo() {
        if (serverControlHandler.checkConnect()) {
            if (!CheckConnectHandler.checkBasicsInfo()) {
                serverControlHandler.getEssentialInfo();
            }
            ServerInfoDto serverInfoDto = new ServerInfoDto();
            serverInfoDto.setServerInfo(CheckConnectHandler.CheckConnect().getServerInfo());
            serverInfoDto.setCpuInfo(CheckConnectHandler.CheckConnect().getCpuInfo());
            serverInfoDto.setMemoryInfo(CheckConnectHandler.CheckConnect().getMemoryInfo());
            serverInfoDto.setDiskInfo(CheckConnectHandler.CheckConnect().getDiskInfo());
            return R.ok(serverInfoDto);
        }
        log.error("服务器未连接");
        return R.fail();
    }
}
