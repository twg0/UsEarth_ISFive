package com.isfive.usearth.web.maker.dto.register_request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MakerRegisterRequest {

	@NotBlank(message = "메이커명을 입력해주세요.")
	private String name;

	@NotBlank(message = "이메일을 입력해주세요.")
	private String email;

	@NotBlank(message = "전화번호를 입력해주세요.")
	private String phone;

}
