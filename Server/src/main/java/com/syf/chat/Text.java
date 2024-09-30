package com.syf.chat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Text {
    public static void main(String[] args) {
        try {
            // 1、创建Socket对象，并请求与服务端程序的连接
            Socket socket = new Socket("127.0.0.1", 1234);

            // 2、获取输出流
            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("请说：");
                String msg = sc.nextLine();

                // 如果用户输入 "exit"，退出客户端
                if ("exit".equals(msg)) {
                    System.out.println("欢迎您下次光临！退出成功！");
                    dos.close(); // 关闭输出流
                    socket.close(); // 关闭 socket
                    break;
                }

                // 将字符串转换为字节数组
                byte[] byteArray = msg.getBytes(); // 获取字符串的字节数组

                // 发送字节数组
                dos.write(byteArray); // 发送字节数组
                dos.flush(); // 确保数据被发送

                // 读取服务器的响应
                String response = in.readLine(); // 读取服务器的响应
                System.out.println("Server response: " + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
