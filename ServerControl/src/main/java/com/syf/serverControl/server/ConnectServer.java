package com.syf.serverControl.server;

import com.syf.serverControl.entity.R;
import com.syf.serverControl.entity.vo.ServerInfoVo;

public interface ConnectServer {
    /**
     * 连接服务器
     */
    R connectServer(ServerInfoVo serverInfoVo);

    /**
     * 断开服务器
     */
    R disconnect();

    /**
     * 获取基本信息
     * @return
     */
    R getEssentialInfo();
}
