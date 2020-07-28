package com.wjjzst.base.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @Author: Wjj
 * @Date: 2020/7/29 12:37 上午
 * @desc:
 */
public class Client {
    public static void main(String[] args) {
        new Client().clientStart();
    }

    private void clientStart() {
        EventLoopGroup workers = new NioEventLoopGroup(5);// worker可以有指定数量
        Bootstrap b = new Bootstrap();
        b.group(workers)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        System.out.println("channel initialized!");
                        ch.pipeline().addLast(new ClientHandler());
                    }
                });
        try {
            System.out.println("start to connect...");
            b.connect("127.0.0.1", 8080).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workers.shutdownGracefully();
        }
    }
}

class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { // 一旦通道建立之后就往外写
        // super.channelActive(ctx);
        System.out.println("channel is activated.");
        final ChannelFuture f = ctx.writeAndFlush(Unpooled.copiedBuffer("HelloNetty", StandardCharsets.UTF_8));
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                System.out.println("msg send!");
                // ctx.close();
            }
        });
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // super.channelRead(ctx, msg);
        try {
            ByteBuf buf = (ByteBuf) msg;
            System.out.println(buf.toString());
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
}