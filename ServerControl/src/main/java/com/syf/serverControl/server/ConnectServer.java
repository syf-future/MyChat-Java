package com.syf.serverControl.server;

import com.syf.serverControl.entity.R;
import com.syf.serverControl.entity.vo.ServerInfoVo;

public interface ConnectServer {
    /**
     * 连接服务器
     */
    R<Object> connectServer(ServerInfoVo serverInfoVo);

    /**
     * 断开服务器
     */
    R<Object> disconnect();

    /**
     * 获取基本信息
     */
    R<Object> getEssentialInfo();
}
