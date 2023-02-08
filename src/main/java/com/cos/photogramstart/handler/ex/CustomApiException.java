package com.cos.photogramstart.handler.ex;

import java.util.Map;

public class CustomApiException extends RuntimeException{

	//객체를 구분할때(JVM이 신경쓸문제)
	private static final long serialVersionUID = 1L;
	
	 private String message;
	
	 
	 public CustomApiException(String message) {
			super(message);
					 
		 }
	 
}
