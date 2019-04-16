package com.wcs.learn.netty.detailProtocal;

/**
 * @author 吴聪帅
 * @Description
 * @Date : 下午7:39 2019/4/10 Modifyby:
 **/
public enum MessageType {

    LOGIN_REQ((byte)0x01),
    LOGIN_RESP((byte)0x02),
    HEARTBEAT_REQ((byte)0x03),
    HEARTBEAT_RESP((byte)0x04),
    ;
    MessageType(byte value) {
        this.value = value;
    }
    private byte value;

    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
    }
}
