package com.wcs.learn.netty.detailProtocal;

import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.Marshaller;

/**
 * @author 吴聪帅
 * @Description
 * @Date : 下午2:02 2019/4/10 Modifyby:
 **/
public class MarshallingEncode {
    private static final byte[] LENGTH_PALCEHOLDER = new byte[4];
    private Marshaller marshaller;
    public MarshallingEncode() throws Exception{
        this.marshaller = MarshallingCodecFactory.buildMarshalling();
    }


    protected void encode(Object msg, ByteBuf out) throws Exception {
        try {
            int lengthPos = out.writerIndex();
            out.writeBytes(LENGTH_PALCEHOLDER);
            ChannelBufferByteOutput channelBufferByteOutput = new ChannelBufferByteOutput(out);
            marshaller.start(channelBufferByteOutput);
            marshaller.writeObject(msg);
            marshaller.finish();
            out.setInt(lengthPos,out.writerIndex()-lengthPos-4);
        }catch (Exception e) {
        }
        finally {
            marshaller.close();
        }
    }
}
