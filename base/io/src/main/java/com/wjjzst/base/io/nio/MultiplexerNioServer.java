package com.wjjzst.base.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: Wjj
 * @Date: 2020/7/26 11:16 上午
 * @desc: 单线程NIO Server
 */
public class MultiplexerNioServer implements Runnable {
    private ServerSocketChannel servChannel;
    private Selector selector; // 多路复用器、时间轮询器
    private volatile boolean stop = false;


    public MultiplexerNioServer(int port) {
        try {
            servChannel = ServerSocketChannel.open(); // 获得一个serverChannel
            selector = Selector.open(); // 获得一个多路复用器
            servChannel.configureBlocking(false); // 设置为非阻塞
            servChannel.socket().bind(new InetSocketAddress(port), 1024); //绑定一个端口 backlog 等待队列长度
            servChannel.register(selector, SelectionKey.OP_ACCEPT); // 把selector注册到channel,关注连接事件
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);// 1为正常退出 会等待其他还在执行任务的线程结束之后再结束 0为非正常退出 立即停止 类似kill -9
        }
    }

    public void stop() {
        this.stop = true;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                int client = selector.select(); // 阻塞
                System.out.println("1: " + client);
//                int client = selector.select(); 不设置超时事件为线程阻塞,但是IO上支持多个
                if (client == 0) {
                    continue;
                }
                Set<SelectionKey> selectionKeys = selector.selectedKeys();// 事件集合
                Iterator<SelectionKey> it = selectionKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();
                    try {
                        handle(key);
                    } catch (Exception e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void handle(SelectionKey key) throws IOException {
        if (key.isValid()) {
            // 连接事件
            if (key.isAcceptable()) {
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept(); // 3次握手
                sc.configureBlocking(false);
                // sc.register(key.selector(), SelectionKey.OP_READ); // 连接建立后关注读事件
                sc.register(selector, SelectionKey.OP_READ); // 连接建立后关注读事件
            }
            // 读事件
            if (key.isReadable()) {
                SocketChannel sc = (SocketChannel) key.channel();
                // ByteBuffer readBuffer = ByteBuffer.allocateDirect(1024); // 写 0 1024 1024 分配堆外内存
                ByteBuffer readBuffer = ByteBuffer.allocate(1024); // 写 0 1024 1024
                int readBytes = sc.read(readBuffer);
                if (readBytes > 0) {
                    // System.out.println(new String(readBuffer.array(),0,readBytes));
                    readBuffer.flip(); // **读写模式反转**
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, "UTF-8");
                    System.out.println("input is:" + body);
                    res(sc, body);
                }
                // ByteBuffer bufferToWrite = ByteBuffer.wrap("HelloClient".getBytes()); //配合上面是马士兵的处理逻辑
                // sc.write(bufferToWrite);
            }
        }
    }

    private void res(SocketChannel channel, String response) throws IOException {
        if (response != null && response.length() > 0) {
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
            System.out.println("res end");
        }

    }
}
