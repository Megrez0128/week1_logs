package org.example;
// 新建一个exception类，标记在主函数发生错误
public class MainException extends RuntimeException {
    public MainException(Exception e) {
        System.out.println("main函数出现异常");
    }
}