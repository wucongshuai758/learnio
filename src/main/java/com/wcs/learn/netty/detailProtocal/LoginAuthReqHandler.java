package com.wcs.learn.netty.detailProtocal;

/**
 * @author 吴聪帅
 * @Description
 * @Date : 下午7:37 2019/4/10 Modifyby:
 **/

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 登录请求
 */
public class LoginAuthReqHandler extends ChannelHandlerAdapter {



    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(buildLoginReq());
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
        throws Exception {
        NettyMessage message = (NettyMessage) msg;

        // 如果是握手应答消息，需要判断是否认证成功
        if (message.getHeader() != null
            && message.getHeader().getType() == MessageType.LOGIN_RESP
            .getValue()) {
            byte loginResult = (byte) message.getBody();
            if (loginResult != (byte) 0) {
                // 握手失败，关闭连接
                ctx.close();
            } else {
                System.out.println("Login is ok : " + message);
                ctx.fireChannelRead(msg);
            }
        } else {
            //调用下一个channel链..
            ctx.fireChannelRead(msg);
        }
    }

    /**
     * 构建登录请求
     */
    private NettyMessage buildLoginReq() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.LOGIN_REQ.getValue());
        message.setHeader(header);
        return message;
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
        throws Exception {
        ctx.fireExceptionCaught(cause);
    }
}
