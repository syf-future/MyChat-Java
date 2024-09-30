package com.syf.chat;

import com.syf.chat.common.config.ServerConfig;
import com.syf.chat.common.config.ThreadPoolConfig;
import com.syf.chat.common.utils.ThreadLocalUtil;
import com.syf.chat.common.utils.ThreadUtil;
import com.syf.chat.handler.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ServerController {
    private ServerConfig serverConfig;
    private ThreadPoolExecutor threadPoolExecutor;
    /**
     * 初始化
     */
    public boolean initialize() {
        ThreadPoolConfig threadPoolConfig = new ThreadPoolConfig();
        serverConfig = new ServerConfig();
        threadPoolExecutor = ThreadUtil.createThreadPool(threadPoolConfig);
        return true;
    }

    /**
     * 启动
     */
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(serverConfig.getPort())) {
            System.out.println("Server is listening on port " + serverConfig.getPort());

            while (true) {
                // 接受客户端连接
                Socket clientSocket = serverSocket.accept();
                System.out.println("新的客户端连接: " + clientSocket.getInetAddress());
                // 将客户端请求交给线程池处理
                threadPoolExecutor.submit(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭线程池
            threadPoolExecutor.shutdown();
        }
    }

    /**
     * 关闭
     */
    public void shutdown() {
        // 关闭线程池
        if (threadPoolExecutor != null) {
            threadPoolExecutor.shutdown(); // 关闭线程池，不再接收新的任务

            try {
                // 等待当前任务完成（可选）
                if (!threadPoolExecutor.awaitTermination(60, TimeUnit.SECONDS)) {
                    // 强制关闭线程池
                    threadPoolExecutor.shutdownNow();
                }
            } catch (InterruptedException e) {
                // 如果当前线程在等待时被中断，强制关闭
                threadPoolExecutor.shutdownNow();
                Thread.currentThread().interrupt(); // 重新设置中断标志
            }
        }

        // 关闭服务器套接字（如果有引用的话）
//        try {
//            if (serverSocket != null && !serverSocket.isClosed()) {
//                serverSocket.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        ThreadLocalUtil.remove();

        System.out.println("服务端已关闭");
    }


}
