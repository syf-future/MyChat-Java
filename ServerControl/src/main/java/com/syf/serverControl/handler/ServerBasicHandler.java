package com.syf.serverControl.handler;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.Session;
import com.syf.serverControl.entity.vo.ServerInfoVo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 服务器基本处理类
 */
public abstract class ServerBasicHandler {
    public Session session;

    /**
     * 连接服务器
     *
     * @param serverInfoVo 服务器信息
     */
    abstract void startConnect(ServerInfoVo serverInfoVo);

    /**
     * 关闭连接
     */
    abstract void closeConnect();

    /**
     * 检查连接状态
     */
    abstract boolean checkConnect();

    /**
     * 向服务器发送命令
     *
     * @param command 命令
     * @return
     */
    public String executeCommand(String command) {
        StringBuilder output = new StringBuilder();
        try {
            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);

            channel.setInputStream(null);
            ((ChannelExec) channel).setErrStream(System.err);

            InputStream input = channel.getInputStream();
            channel.connect();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
            }

            channel.disconnect();
        } catch (Exception e) {
            throw new RuntimeException("命令执行失败: " + e.getMessage(), e);
        }
        return output.toString();
    }
}
