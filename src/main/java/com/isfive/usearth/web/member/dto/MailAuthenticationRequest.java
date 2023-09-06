package com.isfive.usearth.web.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MailAuthenticationRequest {
	@NotBlank(message = "Email 주소를 입력해주세요.")
	private String email;
	@NotBlank(message = "인증 번호를 입력해주세요.")
	private String code;
}
