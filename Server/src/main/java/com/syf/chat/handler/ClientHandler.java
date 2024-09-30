package com.syf.chat.handler;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 客户端处理类
 */
public class ClientHandler implements Runnable {
    private static final Map<String, Socket> socketMap = new ConcurrentHashMap<>(); // 线程安全的客户端列表
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread() + "开始处理");
        try (
                InputStream input = socket.getInputStream();
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        ) {
            byte[] buffer = new byte[1024]; // 创建缓冲区
            int bytesRead;
            StringBuilder message = new StringBuilder();
            // 处理客户端发送的消息
            while ((bytesRead = input.read(buffer)) != -1) {
                message.append(new String(buffer, 0, bytesRead)); // 将字节数组转换为字符串
                System.out.println("接收到客户端请求: " + message.toString());
                // 发送回响应
                output.println("Server: " + message.toString());
                message.setLength(0); // 清空消息
            }
        } catch (SocketException e) {
            System.err.println("连接重置: " + e.getMessage());
            if (socket.isClosed()) {
                System.out.println("客户端已断开连接: " + socket.getInetAddress() + " : " + socket.getPort());
            }
        } catch (IOException e) {
            System.err.println("IO异常: " + e.getMessage());
        } finally {
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close(); // 关闭客户端连接
                    System.out.println("客户端已断开连接: " + socket.getInetAddress());
                }
            } catch (IOException e) {
                System.err.println("关闭连接时异常: " + e.getMessage());
            }
        }
    }
}
