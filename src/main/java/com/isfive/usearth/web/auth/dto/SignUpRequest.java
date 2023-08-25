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
public class SignUpRequest {
	@NotBlank(message = "ID를 입력하세요")
	private String username;
	@NotBlank(message = "닉네임을 입력하세요")
	private String nickname;
	@NotBlank(message = "비밀번호를 입력하세요")
	private String password;
	@NotBlank(message = "비밀번호를 한번 더 입력하세요")
	private String passwordCheck;
	private String phone;
	@NotBlank(message = "Email 주소를 입력하세요")
	private String email;

	public boolean pwCheck() {
		if (password.equals(passwordCheck)) {
			return true;
		}
		return false;
	}
}
