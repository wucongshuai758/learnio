package com.wcs.learn.netty.serial;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

/**
 * @author 吴聪帅
 * @Description
 * @Date : 下午10:31 2019/3/24 Modifyby:
 **/
public class PerformsTestUserInfo {
    public static void main(String[] args) throws IOException{
        UserInfo info = new UserInfo();
        info.setUserID(100);
        info.setUserName("welcome to netty");
        int loop = 100000;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream os = null;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < loop; i++) {
            bos = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bos);
            os.writeObject(info);
            os.flush();
            os.close();
            byte[] b = bos.toByteArray();
            bos.close();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("---------jbk serializable cost time"+(endTime - startTime)+" ms");
        System.out.println("-------");
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        startTime = System.currentTimeMillis();
        for (int i = 0; i < loop; i++) {
            byte[] b = info.codeC();
        }
        endTime = System.currentTimeMillis();
        System.out.println("---------the byte array serializable cost time is"+(endTime - startTime)+" ms");
    }
}
