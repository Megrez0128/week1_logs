package org.example;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.IOException;

// 新建一个exception类，标记在主函数发生错误
public class MainException extends RuntimeException {
    public MainException(Exception e) {
        if(e instanceof NullPointerException){
            System.out.println("输入了错误的参数！");
        }
        else if(e instanceof IOException){
            System.out.println("无法写入文件！");
        }
        else if(e instanceof NumberFormatException){
            System.out.println("输入了错误的参数！");
        }
        else{
            System.out.println(e);
        }
    }
}