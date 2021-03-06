package com.wcs.learn.netty.serial;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author 吴聪帅
 * @Description
 * @Date : 下午11:27 2019/3/7 Modifyby:
 **/
public class SubReqClientHandler extends ChannelHandlerAdapter {

    public SubReqClientHandler() {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i< 10000; i++) {
            ctx.write(subReq(i));
        }
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Service accept client subsribe resp:[" + msg+"]");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private SubscribeResp subReq(int i) {
        SubscribeResp subscribeResp = new SubscribeResp();
        subscribeResp.setSubReqId(i);
        subscribeResp.setDesc("wcs");
        return subscribeResp;
    }
}
