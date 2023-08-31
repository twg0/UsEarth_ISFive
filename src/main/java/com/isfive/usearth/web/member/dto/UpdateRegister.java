package com.isfive.usearth.web.member.dto;

import com.isfive.usearth.domain.member.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRegister {
	private String nickname;
	private String phone;
	private String email;
}
