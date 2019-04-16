package com.wcs.learn.netty.serial;

import lombok.Data;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * @author 吴聪帅
 * @Description
 * @Date : 下午9:36 2019/3/24 Modifyby:
 **/
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -3498249724990274743L;

    private String userName;

    private int userID;

    public byte[] codeC() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        byte[] value = this.userName.getBytes();
        buffer.putInt(value.length);
        buffer.put(value);
        buffer.putInt(this.userID);
        buffer.flip();
        value = null;
        byte[] result = new byte[buffer.remaining()];
        buffer.get(result);
        return result;
    }
}
