package com.wjjzst.base.io.nio;

/**
 * @Author: Wjj
 * @Date: 2020/7/26 11:17 上午
 * @desc:
 */
public class NioServer {
    public static void main(String[] args) {
        int port = 8080;
        MultiplexerNioServer nioServer = new MultiplexerNioServer(port);
        new Thread(nioServer, "nioserver-001").start();
    }
}
