package com.wcs.learn.netty.detailProtocal;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author 吴聪帅
 * @Description
 * @Date : 下午11:05 2019/4/9 Modifyby:
 **/
@Data
public final class NettyMessage {
    private Header header;
    private Object body;
    public static NettyMessage build() {
        NettyMessage nettyMessage = new NettyMessage();
        Header header = new Header();
        header.setPriority((byte)0xC);
        header.setType((byte)0xC);
        Random random = new Random();
        header.setSessionID(random.nextLong());
        Map<String, Object> attachment = new HashMap<>();
        attachment.put("1","wcs");
        header.setAttachment(attachment);
        nettyMessage.setHeader(header);
        nettyMessage.setBody("hahahaha");
        return nettyMessage;
    }
}
