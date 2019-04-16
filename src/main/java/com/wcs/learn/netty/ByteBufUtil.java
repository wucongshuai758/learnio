package com.wcs.learn.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * @author 吴聪帅
 * @Description
 * @Date : 下午11:30 2019/3/20 Modifyby:
 **/
public class ByteBufUtil {
    public static String ByteBuf2String(ByteBuf byteBuf) {
        ByteBuf tempBuf = Unpooled.copiedBuffer(byteBuf);
        byte[] req = new byte[tempBuf.readableBytes()];
        tempBuf.readBytes(req);
        try {
            return new String(req,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        ByteBuf tempBuf = Unpooled.buffer(1024);
        tempBuf.writeInt(123);
        tempBuf.writeInt(234);
        tempBuf.writeInt(345);
        //tempBuf.readByte();
        byte[] bytes = new byte[12];
        ByteBuf byteBuf = tempBuf.slice(0,10);
        byteBuf.setByte(0,(byte)0x01);
        System.out.println(byteBuf.readByte());
        System.out.println(tempBuf.readByte());
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(512);

        //ByteBuf byteBuf=  tempBuf.readBytes(bytes,1,9);
        int newCapacity = 64;

        //System.out.println(newCapacity <<= 1);
    }
}
