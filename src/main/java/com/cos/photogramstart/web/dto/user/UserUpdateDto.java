package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class UserUpdateDto {
	private String name; //필수
	private String password;//필수
	private String website;
	private String bio;
	private String phone;
	private String gender;
	
	
	//조금 위험함. 코드 수정이 필요할 예정
	public User toEntity() {
		return User.builder()
				.name(name)//이름을 기재 안했으면 문제!! Validation 체크!
				.password(password) // 만약 사용자가 패스워드를 기제안했으면 공백의 페스워드가 들어가면 문제가 됨 Validaiton 체크
				.website(website)
				.phone(phone)
				.gender(gender)
				.build();
	}
}
