package com.wcs.learn.netty.serial;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author 吴聪帅
 * @Description
 * @Date : 下午11:13 2019/3/7 Modifyby:
 **/
public class SubReqServerHandler  extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeResp resp = (SubscribeResp) msg;
        if ("wcs".equals(resp.getDesc())) {
            System.out.println("Service accept client subsribe resp:[" + resp.toString()+"]");
            ctx.writeAndFlush(buildResponse(resp.getSubReqId()));
        }
    }

    private SubscribeResp buildResponse(int subReqId) {
        SubscribeResp resp = new SubscribeResp();
        resp.setSubReqId(subReqId);
        resp.setRespCode(0);
        resp.setDesc("receive success");
        return resp;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
