package com.wcs.learn.netty.myProtocal;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;


/**
 * @author 吴聪帅
 * @Description
 * @Date : 下午9:48 2019/4/6 Modifyby:
 **/
public class MyProtocolDecoder extends LengthFieldBasedFrameDecoder {

    private static final int HEADER_SIZE = 6;

    public MyProtocolDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment,
                             int initialBytesToStrip, boolean failFast) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip, failFast);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf frame = (ByteBuf) super.decode(ctx, in);
        if (frame == null) {
            return null;
        }
        if (frame.readableBytes() < HEADER_SIZE) {
            throw new Exception("字节数不足");
        }
        byte type = frame.readByte();
        byte flag = frame.readByte();
        int length = frame.readInt();
        if(frame.readableBytes()!=length){
            throw new Exception("标记的长度不符合实际长度");
        }
        //读取body
        byte []bytes = new byte[frame.readableBytes()];
        frame.readBytes(bytes);
        return new MyProtocolBean(type,flag,length,new String(bytes,"UTF-8"));

    }


}
