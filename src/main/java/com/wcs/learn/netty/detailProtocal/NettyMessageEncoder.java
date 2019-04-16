package com.wcs.learn.netty.detailProtocal;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.UnsupportedEncodingException;

/**
 * @author 吴聪帅
 * @Description
 * @Date : 下午11:11 2019/4/9 Modifyby:
 **/
public class NettyMessageEncoder extends MessageToByteEncoder<NettyMessage> {
    MarshallingEncode marshallingEncode;

    {
        try {
            marshallingEncode = new MarshallingEncode();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage msg, ByteBuf out) throws Exception {
        if (msg == null) {
            throw new Exception("msg is null");
        }
        out.writeInt(msg.getHeader().getCrcCode());
        int lengthPos = out.writerIndex();
        out.writeInt(msg.getHeader().getLength());
        out.writeLong(msg.getHeader().getSessionID());
        out.writeByte(msg.getHeader().getType());
        out.writeByte(msg.getHeader().getPriority());
        out.writeInt(msg.getHeader().getAttachment().size());
        if (msg.getHeader().getAttachment().size() > 0) {
            for (String k : msg.getHeader().getAttachment().keySet()) {
                try {
                    Object v = msg.getHeader().getAttachment().get(k);
                    byte[] keyByteArray = k.getBytes("UTF-8");
                    out.writeInt(keyByteArray.length);
                    out.writeBytes(keyByteArray);
                    marshallingEncode.encode(v, out);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if (msg.getBody() != null) {
            marshallingEncode.encode(msg.getBody(),out);
        } else {
            out.writeInt(0);
        }
        out.setInt(lengthPos,out.writerIndex()-lengthPos);


    }
}
