package com.wjjzst.base.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;


/**
 * @Author: Wjj
 * @Date: 2020/7/28 8:03 下午
 * @desc:
 */
public class HelloNetty {
    public static void main(String[] args) {
        new NettyServer(8080).serverStart();
    }
}

class NettyServer {
    int port = 8888;

    public NettyServer(int port) {
        this.port = port;
    }

    public void serverStart() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // 第一个boss Select  专门负责连接
        EventLoopGroup workerGroup = new NioEventLoopGroup(); // 第二个worker Select 专门负责连接之后的IO处理
        ServerBootstrap b = new ServerBootstrap();// 鞋带  一拽鞋带所有的都动了
        b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)     // 定义连接通道类型
                .childHandler(new ChannelInitializer<SocketChannel>() {  // 通道初始化   每个客户端连接上来之后给他一个监听器来进行处理
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new Handler());// 监听器处理逻辑 在通道上 加一个队列通道的处理器

                    }
                });
        try {
            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}

class Handler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // super.channelRead(ctx, msg);
        System.out.println("Server: channel read");
        ByteBuf buf = (ByteBuf) msg;
        System.out.println(buf.toString(CharsetUtil.UTF_8));
        ctx.writeAndFlush(msg);
        ctx.close();
        // buf.release();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        ctx.close();
    }
}
