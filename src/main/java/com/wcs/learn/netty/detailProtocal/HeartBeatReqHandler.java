package com.wcs.learn.netty.detailProtocal;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.ScheduledFuture;

import java.util.concurrent.TimeUnit;

/**
 * @author 吴聪帅
 * @Description
 * @Date : 下午7:58 2019/4/10 Modifyby:
 **/
public class HeartBeatReqHandler extends ChannelHandlerAdapter {

    //使用定时任务发送
    private volatile ScheduledFuture<?> heartBeat;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
        throws Exception {
        NettyMessage message = (NettyMessage)msg;
        // 当握手成功后，Login响应向下透传，主动发送心跳消息
        if (message.getHeader() != null
            && message.getHeader().getType() == MessageType.LOGIN_RESP
            .getValue()) {
            if (heartBeat == null) {
                //NioEventLoop是一个Schedule,因此支持定时器的执行，创建心跳计时器
                heartBeat = ctx.executor().scheduleAtFixedRate(
                    new HeartBeatReqHandler.HeartBeatTask(ctx), 0, 5000,
                    TimeUnit.MILLISECONDS);
            }
            else {
                System.out.println("heart beat not null");
            }
        } else if (message.getHeader() != null
            && message.getHeader().getType() == MessageType.HEARTBEAT_RESP
            .getValue()) {
            System.out.println("Client receive server heart beat message : ---> "
                + message);
        } else { ctx.fireChannelRead(msg); }
    }

    //Ping消息任务类
    private class HeartBeatTask implements Runnable {
        private final ChannelHandlerContext ctx;

        public HeartBeatTask(final ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            NettyMessage heatBeat = buildHeatBeat();
            System.out.println("Client send heart beat messsage to server : ---> "
                + heatBeat);
            ctx.writeAndFlush(heatBeat);
        }

        private NettyMessage buildHeatBeat() {
            NettyMessage message = new NettyMessage();
            Header header = new Header();
            header.setType(MessageType.HEARTBEAT_REQ.getValue());
            message.setHeader(header);
            return message;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
        throws Exception {
        cause.printStackTrace();
        if (heartBeat != null) {
            heartBeat.cancel(true);
            heartBeat = null;
        }
        ctx.fireExceptionCaught(cause);
    }
}