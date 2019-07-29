package com.wjjzst.netty.learn.ch2;

/**
 * @author Wjj
 */
public class ServerBoot {

    private static final int PORT = 8000;

    public static void main(String[] args) {
        Server server = new Server(PORT);
        server.start();
    }

}
