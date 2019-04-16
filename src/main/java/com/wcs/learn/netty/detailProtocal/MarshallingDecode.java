package com.wcs.learn.netty.detailProtocal;

import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.ByteInput;
import org.jboss.marshalling.Unmarshaller;

/**
 * @author 吴聪帅
 * @Description
 * @Date : 下午2:50 2019/4/10 Modifyby:
 **/
public class MarshallingDecode {
    private final Unmarshaller unmarshaller;

    public MarshallingDecode() throws Exception{
        unmarshaller = MarshallingCodecFactory.buildUnMarshalling();
    }

    protected  Object decode(ByteBuf in) throws Exception {
        int objectSize = in.readInt();
        ByteBuf buf = in.slice(in.readerIndex(),objectSize);
        ByteInput input = new ChannelBufferByteInput(buf);
        try {
            unmarshaller.start(input);
            Object obj = unmarshaller.readObject();
            unmarshaller.finish();
            in.readerIndex(in.readerIndex()+objectSize);
            return obj;
        }finally {
            unmarshaller.close();
        }
    }
}
