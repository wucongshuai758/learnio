package com.wcs.learn.netty.detailProtocal;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 吴聪帅
 * @Description
 * @Date : 下午2:49 2019/4/10 Modifyby:
 **/
public class NettyMessageDecode extends LengthFieldBasedFrameDecoder {
    MarshallingDecode marshallingDecode;

    public NettyMessageDecode(int maxFrameLength, int lengthFieldOffset,
                              int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip) throws Exception {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength,lengthAdjustment,initialBytesToStrip);
        marshallingDecode = new MarshallingDecode();
    }
    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf frame = (ByteBuf)super.decode(ctx,in);
        if (frame == null) {
            return null;
        }
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setCrcCode(frame.readInt());
        header.setLength(frame.readInt());
        header.setSessionID(frame.readLong());
        header.setType(frame.readByte());
        header.setPriority(frame.readByte());
        int size = frame.readInt();
        if (size > 0) {
            Map<String,Object> attch = new HashMap<>();
            int keySize = 0;
            byte[] keyArray = null;
            String key = null;
            for (int i=0;i< size;i++) {
                keySize = frame.readInt();
                keyArray = new byte[keySize];
                frame.readBytes(keyArray);
                key = new String(keyArray,"UTF-8");
                attch.put(key,marshallingDecode.decode(frame));
            }
            keyArray = null;
            key = null;
            header.setAttachment(attch);
        }
        if (frame.readableBytes() > 4) {
            message.setBody(marshallingDecode.decode(frame));
        }
        message.setHeader(header);
        return message;
    }
}
