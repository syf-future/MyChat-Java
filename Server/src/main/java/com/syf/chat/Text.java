package com.syf.chat;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Text {
    private static final int PORT = 12345; // 服务器监听的端口
    private static final int THREAD_POOL_SIZE = 10; // 线程池大小

    public static void main(String[] args) {
        // 创建一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);

            while (true) {
                // 接受客户端连接
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());

                // 将客户端请求交给线程池处理
                executorService.submit(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭线程池
            executorService.shutdown();
        }
    }
}

// 处理客户端请求的类，实现 Runnable 接口
class ClientHandler implements Runnable {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        ) {
            String message;
            // 处理客户端发送的消息
            while ((message = input.readLine()) != null) {
                System.out.println("Received from client: " + message);
                // 发送回响应
                output.println("Server: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close(); // 关闭客户端连接
                System.out.println("Client disconnected: " + socket.getInetAddress());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
