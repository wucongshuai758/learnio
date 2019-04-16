package com.wcs.learn.netty.serial;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @author 吴聪帅
 * @Description
 * @Date : 下午11:21 2019/3/7 Modifyby:
 **/
public class SubReqClient {
    public void connect(int port,String host) throws Exception {
        //创建读写io线程组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new ObjectDecoder(1024 * 1024,
                            ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
                        socketChannel.pipeline().addLast(new ObjectEncoder());

                        socketChannel.pipeline().addLast(new SubReqClientHandler());

                    }
                });
            ChannelFuture f = b.connect(host,port).sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length >0) {
            try {
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e) {

            }
        }
        try {
            new SubReqClient().connect(port,"127.0.0.1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
