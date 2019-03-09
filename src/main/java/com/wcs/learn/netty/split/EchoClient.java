package com.wcs.learn.netty.split;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author 吴聪帅 DelimiterBasedFrameDecoder固定字符编解码
 * @Description
 * @Date : 下午5:20 2019/3/6 Modifyby:
 **/
public class EchoClient {
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
                        ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
                        //  DelimiterBasedFrameDecoder固定字符编解码
                        //socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter));
                        // FixedLengthFrameDecoder 指定字符编辑码
                        socketChannel.pipeline().addLast(new FixedLengthFrameDecoder(20));
                        socketChannel.pipeline().addLast(new StringDecoder());
                        socketChannel.pipeline().addLast(new EchoClientHandler());

                    }
                });
            System.out.println("2");
            ChannelFuture f = b.connect(host,port).sync();
            System.out.println("3");
            f.channel().closeFuture().sync();
            System.out.println("4");
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
            new EchoClient().connect(port,"127.0.0.1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
