package com.isfive.usearth.web.maker.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MakerUpdateRequest {
	@NotBlank(message = "전화번호를 입력해주세요.")
	private String phone;
	@NotBlank(message = "이메일을 입력해주세요.")
	private String email;
}
