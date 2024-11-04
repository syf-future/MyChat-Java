package com.syf.serverControl.controller;

import com.syf.serverControl.entity.R;
import com.syf.serverControl.entity.vo.ServerInfoVo;
import com.syf.serverControl.server.ConnectServer;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务器连接控制类
 */
@RestController
@RequestMapping("/server/control")
public class ConnectController {
    @Resource
    private ConnectServer connectServer;

    /**
     * 连接服务器
     */
    @PostMapping("/connect")
    private R connectServer(@RequestBody ServerInfoVo serverInfoVo) {
        return connectServer.connectServer(serverInfoVo);
    }

    /**
     * 获取服务器基本信息
     */
    @PostMapping("/getEssentialInfo")
    private R getEssentialInfo() {
        return connectServer.getEssentialInfo();
    }

    /**
     * 断开服务器
     */
    @PostMapping("/disconnect")
    private R disconnect() {
        return connectServer.disconnect();
    }
}
