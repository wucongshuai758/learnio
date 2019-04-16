package com.wcs.learn.netty.detailProtocal;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author 吴聪帅
 * @Description
 * @Date : 下午10:06 2019/4/6 Modifyby:
 **/
public class Client {
    private ScheduledExecutorService executor = Executors
        .newScheduledThreadPool(1);

    EventLoopGroup group = new NioEventLoopGroup();

    public void connect(int port, String host) throws Exception {

        // 配置客户端NIO线程组

        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch)
                        throws Exception {
                        ch.pipeline().addLast("readTimeoutHandler",
                            new ReadTimeoutHandler(150));
                        ch.pipeline().addLast(new NettyMessageDecode(1024 * 1024, 4, 4, -4, 0));
                        ch.pipeline().addLast("MessageEncoder",
                            new NettyMessageEncoder());
                        ch.pipeline().addLast("LoginAuthHandler",
                            new LoginAuthReqHandler());
                        ch.pipeline().addLast("HeartBeatHandler",
                            new HeartBeatReqHandler());
                    }
                });
            // 发起异步连接操作
            ChannelFuture future = b.connect(
                new InetSocketAddress(host, port)).sync();
            // 当对应的channel关闭的时候，就会返回对应的channel。
            // Returns the ChannelFuture which will be notified when this channel is closed. This method always returns the same future instance.
            future.channel().closeFuture().sync();
        } finally {
            System.out.println("server connect timeout start reconnect");
            // 所有资源释放完成之后，清空资源，再次发起重连操作
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        try {
                            connect(NettyConstant.PORT, NettyConstant.REMOTEIP);// 发起重连操作
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new Client().connect(NettyConstant.PORT, NettyConstant.REMOTEIP);
    }

}
