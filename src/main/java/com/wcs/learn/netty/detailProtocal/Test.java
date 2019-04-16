package com.wcs.learn.netty.detailProtocal;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author 吴聪帅
 * @Description
 * @Date : 下午3:55 2019/4/10 Modifyby:
 **/
public class Test {
    public static void main(String[] args) throws Exception {
        NettyMessage nettyMessage = NettyMessage.build();
        NettyMessageEncoder nettyMessageEncoder = new NettyMessageEncoder();
        NettyMessageDecode nettyMessageDecode = new NettyMessageDecode(1024*1024,4,4,-4,0);
        ByteBuf buffer = Unpooled.buffer(1024);
        try {
            nettyMessageEncoder.encode(null,nettyMessage,buffer);
            nettyMessageDecode.decode(null,buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
