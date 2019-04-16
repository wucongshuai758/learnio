package com.wcs.learn.netty.myProtocal;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author 吴聪帅
 * @Description
 * @Date : 下午10:19 2019/4/6 Modifyby:
 **/
public class ClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 100; i++) {
            MyProtocolBean myProtocolBean = new MyProtocolBean((byte)0xA, (byte)0xC, "Hello,Netty".length(),
                "Hello,Netty");
            ctx.writeAndFlush(myProtocolBean);
        }

    }
}
