package com.isfive.usearth.web.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignInRequest {
	@NotBlank(message = "ID를 입력하세요.")
	private String username;
	@NotBlank(message = "비밀번호를 입력하세요.")
	private String password;
}
