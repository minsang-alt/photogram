package com.cos.photogramstart.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final 필드를 DI할때 사용 즉 final인 변수들을 생성자를 이용해 주입?
@Controller //1.IoC 2.파일을 리턴하는 컨트롤러
public class AuthController {
	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	private final AuthService authService;
	
	//public AuthController(AuthService authService) {
	//	this.authService = authService;
	//} AuthController IoC할때 생성자도 실행이 되는데 만약 AuthService가 이미 IoC가 되어야 dispatcher가 찾아서 의존성 주입을 할수 있지만 없다면 불가능하여 오류가 생기기때문에 이렇게 안하고 어노테이션으로 관리하는것이 좋다.
	
	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}
	
	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}
	//회원가입버튼->/auth/signup->/auth/signin
	@PostMapping("/auth/signup")
	public String signup(SignupDto signupDto) { // 폼형태로 날라오니 key=value (x-www-form-urlencoded)
		//log.info(signupDto.toString());
		//User <-SignupDto
		User user = signupDto.toEntity();
		//log.info(user.toString());
		User userEntity = authService.회원가입(user);
		System.out.println(userEntity);
		return "auth/signin";
	}
}
