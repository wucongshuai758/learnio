package com.wcs.learn.netty.uploadfile;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @author 吴聪帅
 * @Description
 * @Date : 下午7:23 2019/4/8 Modifyby:
 **/
public class FileUploadClient {
    public void connect(int port, String host,
                        final FileUploadFile fileUploadFile) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<Channel>() {

                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast(new ObjectEncoder());
                        ch.pipeline()
                            .addLast(
                                new ObjectDecoder(
                                    ClassResolvers
                                        .weakCachingConcurrentResolver(null)));
                        ch.pipeline()
                            .addLast(
                                new FileUploadClientHandler(
                                    fileUploadFile));
                    }
                });
            ChannelFuture f = b.connect(host, port).sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {

    }
}
