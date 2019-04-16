package com.wcs.learn.netty.httpfile;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author 吴聪帅
 * @Description
 * @Date : 下午10:44 2019/3/9 Modifyby:
 **/
public class HttpFileServer {
    private static final String DEFAULT_URL = "/Users/congshuai.wcs";

    private static final String HOST = "127.0.0.1";

    public void run(final int port,final String url) throws Exception {
        //创建两个线程组 一个用于服务端接收客户端的连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //一个用于网络读写
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast("http-encoder",new HttpResponseEncoder());
                        ch.pipeline().addLast("http-decoder",new HttpRequestDecoder());
                        ch.pipeline().addLast("http-aggregator",new HttpObjectAggregator(65536));
                        ch.pipeline().addLast("http-chunked",new ChunkedWriteHandler());
                        ch.pipeline().addLast("fileServerHandler",new HttpFileServerHandler(url));

                    }
                });
            ChannelFuture future = b.bind(HOST,port).sync();
            System.out.println("HTTP 文件服务器启动。网址是：http://"+HOST+":"+port+url);
            future.channel().closeFuture().sync();
        }
        finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {

            }
        }
        try {
            new HttpFileServer().run(port,DEFAULT_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
