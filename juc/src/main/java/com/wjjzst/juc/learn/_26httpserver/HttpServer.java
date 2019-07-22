package com.wjjzst.juc.learn._26httpserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: Wjj
 * @Date: 2019/7/22 8:30
 * @desc:
 */
public class HttpServer {
    public static void main(String[] args) throws IOException {
        // 启动服务器 监听8888端口
        ServerSocket server = new ServerSocket(8888);
        while (Thread.interrupted()) {
            //不停接收客户端请求
            Socket client = server.accept();
            //获取输入流
            InputStream ins = client.getInputStream();
            OutputStream out = client.getOutputStream();

        }
    }
}
