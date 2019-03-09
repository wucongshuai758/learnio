package com.wcs.learn.NIO;

import java.util.Scanner;

/**
 * @author 吴聪帅
 * @Description
 * @Date : 上午12:06 2019/3/3 Modifyby:
 **/
public class Test {

    //测试主方法
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        //运行服务器
        Server.start();
        //避免客户端先于服务器启动前执行代码
        Thread.sleep(100);
        //运行客户端
        Client.start();
        while (Client.sendMsg(new Scanner(System.in).nextLine())) { ; }
    }

}
