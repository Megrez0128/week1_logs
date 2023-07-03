package org.example.exceptions;

// 新建一个exception类，标记在主函数发生错误
public class MainException extends RuntimeException {
	
	public MainException(String message) {
		System.out.println(message);
	}
	public MainException(String message, Throwable cause) {
		super(message, cause);
	}

}
