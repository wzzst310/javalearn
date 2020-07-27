package com.wjjzst.base.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: Wjj
 * @Date: 2020/7/26 9:30 上午
 * @desc:
 */
public class BioServerSingle {
    public static void main(String[] args) {
        // int port = 8080;
        ServerSocket serverSocket = null; // 服务端对象
        Socket socket = null; // 客户端
        InputStream in = null; // 输入流
        OutputStream out = null; // 输出流
        try {
            serverSocket = new ServerSocket(BioServerConstants.PORT); // 指定端口 监听
            // serverSocket = new ServerSocket();
            // serverSocket.bind(new InetSocketAddress("127.0.0.1",8888));
            ServerHandlerExcutePool pool = new ServerHandlerExcutePool(50, 100);
            while (true) {
                System.out.println("Start");
                socket = serverSocket.accept(); // 阻塞 建立三次握手

                /* 最开始处理逻辑
                in = socket.getInputStream();
                byte[] buffer = new byte[1024];
                int length = 0;
                while ((length = in.read(buffer)) > 0) { // 阻塞
                    System.out.println("input is:" + new String(buffer, 0, length));
                    out = socket.getOutputStream();
                    out.write("success".getBytes());
                }*/
                /* 第二种处理逻辑直接new Thread 无限制不行
                new Thread((new SocketHandler(socket))).start();*/
                pool.execute(new SocketHandler(socket));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
