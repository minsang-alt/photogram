package com.cos.photogramstart.handler;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomValidationException;

@RestController
@ControllerAdvice//예외가 발생했을때 낚아채서 여기로 옴
public class ControllerExceptionhandler {

	@ExceptionHandler(CustomValidationException.class)//이 예외가 발생하는 모든 exception을 이 함수가 가져간다.
	public Map<String,String> validationException(CustomValidationException e) {
		return e.getErrorMap();
	}
}
