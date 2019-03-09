package com.wcs.learn.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author 吴聪帅
 * @Description
 * @Date : 上午11:58 2019/3/6 Modifyby:
 **/
public class TimeClientHandler extends ChannelHandlerAdapter {
    private int count;

    private byte[] req;


    public TimeClientHandler() {
         req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
      //  firstMsg = Unpooled.buffer(req.length);
      //  firstMsg.writeBytes(req);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf message = null;
        for (int i = 0; i< 100; i++) {
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println("Now is : "+body + "the counter is :"+ ++count);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("Unexpected exception from downstream :"+cause.getMessage());
        ctx.close();
    }

}
