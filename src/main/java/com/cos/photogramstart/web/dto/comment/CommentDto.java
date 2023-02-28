package com.cos.photogramstart.web.dto.comment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CommentDto {
	@NotBlank//빈값이거나 null 체크
	private String content;
	@NotNull //빈값체크
	private int imageId;
	
	//toEntity필요없다
}
