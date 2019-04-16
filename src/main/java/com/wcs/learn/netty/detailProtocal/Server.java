package com.wcs.learn.netty.detailProtocal;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * @author 吴聪帅
 * @Description
 * @Date : 下午10:01 2019/4/6 Modifyby:
 **/
public class Server {
    public void bind() throws Exception {
        // 配置服务端的NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
            .option(ChannelOption.SO_BACKLOG, 100)
            .handler(new LoggingHandler(LogLevel.INFO))
            .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch)
                    throws Exception {
                    System.out.println("12222");
                    ch.pipeline().addLast("readTimeoutHandler",
                        new ReadTimeoutHandler(10));
                    ch.pipeline().addLast(new NettyMessageDecode(1024 * 1024, 4, 4, -4, 0));
                    ch.pipeline().addLast(new NettyMessageEncoder());
                    ch.pipeline().addLast(new LoginAuthRespHandler());
                    ch.pipeline().addLast("HeartBeatHandler",
                        new HeartBeatRespHandler());
                }
            });

        // 绑定端口，同步等待成功
        b.bind(NettyConstant.REMOTEIP, NettyConstant.PORT).sync();
       System.out.println("Netty server start ok : "
            + (NettyConstant.REMOTEIP + " : " + NettyConstant.PORT));
    }

    public static void main(String[] args) throws Exception {
        new Server().bind();
    }
}
