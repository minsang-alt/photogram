package com.cos.photogramstart.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.auth.CMRespDto;

@RestController
@ControllerAdvice//예외가 발생했을때 낚아채서 여기로 옴
public class ControllerExceptionhandler {

	@ExceptionHandler(CustomValidationException.class)//이 예외가 발생하는 모든 exception을 이 함수가 가져간다.
	public String validationException(CustomValidationException e) {
		//CMRespDto,Script 비교하면
		//1.클라이언트에게 응답할때는 Script 좋음.
		//2.Ajax통신-CMRespDto
		//3.Android 통신 -CMRespDto
		return Script.back(e.getErrorMap().toString());
		//return new CMRespDto<Map<String,String>>(-1,e.getMessage(),e.getErrorMap());
	
	
	}
	
	@ExceptionHandler(CustomValidationApiException.class)//이 예외가 발생하는 모든 exception을 이 함수가 가져간다.
	public ResponseEntity<?> validationApiException(CustomValidationException e) {
	
		return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),e.getErrorMap()),HttpStatus.BAD_REQUEST);		
	
	
	}
	
	@ExceptionHandler(CustomApiException.class)//이 예외가 발생하는 모든 exception을 이 함수가 가져간다.
	public ResponseEntity<?> apiException(CustomApiException e) {
	
		return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),null),HttpStatus.BAD_REQUEST);		
	
	
	}
}
