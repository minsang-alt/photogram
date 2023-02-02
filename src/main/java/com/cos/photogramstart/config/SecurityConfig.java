package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity //해당 파일로 시큐리티 활성화
@Configuration//IoC  
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean //securityConfig가 IoC에 등록될때 encode()를 들고와서 IoC가 들고있는다.
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	//super 삭제 -기존 시큐리티가 가지고 있는 기능이 비활성화 됨
	http.csrf().disable();
	http.authorizeRequests()
		.antMatchers("/","/user/**","/image/**","/subscribe/**","/comment/**").authenticated()
		.anyRequest().permitAll()
		.and()
		.formLogin()
		.loginPage("/auth/signin") //GET
		.loginProcessingUrl("/auth/signin")//POST->스프링시큐리티가 로그인 프로세스가 진행
		.defaultSuccessUrl("/");
	}
}
