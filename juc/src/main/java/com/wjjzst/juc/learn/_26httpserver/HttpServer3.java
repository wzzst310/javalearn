package com.wjjzst.juc.learn._26httpserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: Wjj
 * @Date: 2019/7/22 8:30
 * @desc:
 */
public class HttpServer3 {
    public static void main(String[] args) throws IOException {
        ExecutorService pool = Executors.newCachedThreadPool();
        // 启动服务器 监听8888端口
        ServerSocket server = new ServerSocket(8888);
        System.out.println("服务器启动,监听" + 8888 + "端口");
        while (!Thread.interrupted()) {
            // 不停接收客户端请求
            Socket client = server.accept();
            pool.execute(new ServerThread(client));
            //new Thread(new ServerThread(client)).start();
        }
        server.close();
    }


}
