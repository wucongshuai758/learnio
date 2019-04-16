package com.wcs.learn.netty.serial;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author 吴聪帅
 * @Description
 * @Date : 下午9:48 2019/3/24 Modifyby:
 **/
public class TestUserInfo {
    public static void main(String[] args) {
        UserInfo info = new UserInfo();
        info.setUserID(100);
        info.setUserName("wcs");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream os  = new ObjectOutputStream(bos);
            os.writeObject(info);
            os.flush();
            os.close();
            byte[] b = bos.toByteArray();
            System.out.println("the jdk serializable length is:"+b.length);
            bos.close();
            System.out.println("-----------");
            System.out.println("the byte array Serializable length is:"+info.codeC().length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
