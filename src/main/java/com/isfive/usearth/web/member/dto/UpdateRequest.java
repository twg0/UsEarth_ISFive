package com.isfive.usearth.web.member.dto;

import lombok.Data;

@Data
public class UpdateRequest {
	private String nickname;
	private String phone;
	private String email;

	public UpdateRegister toRegister() {
		return UpdateRegister.builder()
			.nickname(this.nickname)
			.phone(this.phone)
			.email(this.email)
			.build();
	}
}
