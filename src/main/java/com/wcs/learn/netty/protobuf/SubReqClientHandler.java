package com.wcs.learn.netty.protobuf;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 吴聪帅
 * @Description
 * @Date : 下午10:48 2019/4/7 Modifyby:
 **/
public class SubReqClientHandler  extends ChannelHandlerAdapter {
    public SubReqClientHandler() {

    }

    @Override
    public void channelActive(ChannelHandlerContext context) {
        for(int i=0;i<10;i++) {
            context.write(subReq(i));
        }
        context.flush();
    }

    private SubscribeReqProto.SubscribeReq subReq(int i) {
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqId(i);
        builder.setUserName("wcs");
        builder.setProductName("Netty Book");
        List<String> addressList = new ArrayList<>();
        addressList.add("beijing gugong");
        builder.addAllAddress(addressList);
        return builder.build();
    }

    @Override
    public void channelRead(ChannelHandlerContext context,Object msg) throws Exception{
        System.out.println("received server response :["+msg+"]");
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


}
