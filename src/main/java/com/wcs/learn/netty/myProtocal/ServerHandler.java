package com.wcs.learn.netty.myProtocal;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.ReadTimeoutException;

/**
 * @author 吴聪帅
 * @Description
 * @Date : 下午10:03 2019/4/6 Modifyby:
 **/
public class ServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //直接转化成协议消息实体
        MyProtocolBean myProtocolBean = (MyProtocolBean)msg;
        System.out.println(myProtocolBean.getContent());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        if (cause instanceof ReadTimeoutException) {
            System.out.println("client heartbean timeout");
            // 删除缓存
            ctx.close();
            ctx.fireExceptionCaught(cause);
        }
        else {
            ctx.close();
            ctx.fireExceptionCaught(cause);
        }
    }
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("====channelRegistered===");
        ctx.fireChannelRegistered();
    }
}

