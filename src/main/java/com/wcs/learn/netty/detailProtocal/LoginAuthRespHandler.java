package com.wcs.learn.netty.detailProtocal;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.ReadTimeoutException;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 吴聪帅
 * @Description
 * @Date : 下午7:49 2019/4/10 Modifyby:
 **/
public class LoginAuthRespHandler extends ChannelHandlerAdapter {

    /**
     * 本地缓存
     */
    private Map<String, Boolean> nodeCheck = new ConcurrentHashMap<String, Boolean>();
    private String[] whitekList = {"127.0.0.2", "192.168.1.104"};

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
        throws Exception {
        NettyMessage message = (NettyMessage)msg;

        // 如果是握手请求消息，处理，其它消息透传
        if (message.getHeader() != null
            && message.getHeader().getType() == MessageType.LOGIN_REQ
            .getValue()) {
            String nodeIndex = ctx.channel().remoteAddress().toString();
            NettyMessage loginResp = null;
            // 重复登陆，拒绝
            if (nodeCheck.containsKey(nodeIndex)) {
                loginResp = buildResponse((byte)-1);
            } else {
                InetSocketAddress address = (InetSocketAddress)ctx.channel()
                    .remoteAddress();
                String ip = address.getAddress().getHostAddress();
                boolean isOK = false;
                for (String WIP : whitekList) {
                    if (WIP.equals(ip)) {
                        isOK = true;
                        break;
                    }
                }
                loginResp = isOK ? buildResponse((byte)0)
                    : buildResponse((byte)-1);
                if (isOK) { nodeCheck.put(nodeIndex, true); }
            }
            System.out.println("The login response is : " + loginResp
                + " body [" + loginResp.getBody() + "]");
            ctx.writeAndFlush(loginResp);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private NettyMessage buildResponse(byte result) {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.LOGIN_RESP.getValue());
        message.setHeader(header);
        message.setBody(result);
        return message;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
        throws Exception {

        cause.printStackTrace();
        if (cause instanceof ReadTimeoutException) {
            System.out.println("client heartbean timeout");
            // 删除缓存
            nodeCheck.remove(ctx.channel().remoteAddress().toString());
            ctx.close();
            ctx.fireExceptionCaught(cause);
        }
        else {
            ctx.close();
            ctx.fireExceptionCaught(cause);
        }

    }
}