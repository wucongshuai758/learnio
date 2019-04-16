package com.wcs.learn;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * @author 吴聪帅
 * @Description
 * @Date : 下午5:25 2019/3/1 Modifyby:
 **/
public class Application {
    public static void main(String[] args) {
        //分配88字节空间
        ByteBuffer byteBuffer = ByteBuffer.allocate(88);
        System.out.println("此时 limit = "+byteBuffer.limit()+" position="+byteBuffer.position()+ " capacity="+byteBuffer.capacity());
        String value = "Netty权威指南";
        //向buffer写数据
        byteBuffer.put(value.getBytes());
        System.out.println("写入数据后 limit = "+byteBuffer.limit()+" position="+byteBuffer.position()+ " capacity="+byteBuffer.capacity());
        //翻转buffer
        byteBuffer.flip();
        System.out.println("翻转后 limit = "+byteBuffer.limit()+" position="+byteBuffer.position()+ " capacity="+byteBuffer.capacity());
        byte[] tbyte = new byte[17];
        //从buffer读数据
        byteBuffer.get(tbyte);
        System.out.println("读后 limit = "+byteBuffer.limit()+" position="+byteBuffer.position()+ " capacity="+byteBuffer.capacity());
    }
    @Test
    public void testByteBuf() {
        ByteBuf byteBuf = Unpooled.buffer(88);
        System.out.println("原始 capacity:"+byteBuf.capacity()+" readindex="+byteBuf.readerIndex() + " writeIndex = "+byteBuf.writerIndex());
        String value = "Netty权威指南";
        byteBuf.writeBytes(value.getBytes());
        System.out.println("step1 capacity:"+byteBuf.capacity()+" readindex="+byteBuf.readerIndex() + " writeIndex = "+byteBuf.writerIndex());
        System.out.println("2.ByteBuf中的内容为===============>"+Arrays.toString(byteBuf.array())+"\n");
        byte b1 = byteBuf.readByte();
        byte b2 = byteBuf.readByte();
        System.out.println("step2 capacity:"+byteBuf.capacity()+" readindex="+byteBuf.readerIndex() + " writeIndex = "+byteBuf.writerIndex());
        System.out.println("step2 .ByteBuf中的内容为===============>"+Arrays.toString(byteBuf.array())+"\n");
        System.out.println("step2 .b2 中的内容为===============>"+b2+"\n");

        byteBuf.discardReadBytes();
        System.out.println("step3 capacity:"+byteBuf.capacity()+" readindex="+byteBuf.readerIndex() + " writeIndex = "+byteBuf.writerIndex());
        System.out.println("step3 .ByteBuf中的内容为===============>"+Arrays.toString(byteBuf.array())+"\n");
        byteBuf.setZero(0,byteBuf.capacity());
        System.out.println("step4 capacity:"+byteBuf.capacity()+" readindex="+byteBuf.readerIndex() + " writeIndex = "+byteBuf.writerIndex());
        System.out.println("step4 .ByteBuf中的内容为===============>"+Arrays.toString(byteBuf.array())+"\n");
    }

    @Test
    public void testChannel() {

    }
}
