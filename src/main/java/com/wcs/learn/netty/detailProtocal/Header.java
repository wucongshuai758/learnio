package com.wcs.learn.netty.detailProtocal;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 吴聪帅
 * @Description
 * @Date : 下午11:05 2019/4/9 Modifyby:
 **/
@Data
public final class Header {
    private int crcCode = 0xABEF0101;
    private int length;
    private long sessionID;
    private byte type;
    private byte priority;
    private Map<String, Object> attachment = new HashMap<String, Object>();
}
