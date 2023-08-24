package com.isfive.usearth.domain.member.dto;

import com.isfive.usearth.domain.member.entity.Member;
import com.isfive.usearth.domain.member.entity.Role;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponse {
	private Long id;
	private String username;
	private String nickname;
	private String password;
	private String phone;
	private String email;
	private String provider;
	private String providerId;
	private Role role;

	public static MemberResponse fromEntity(Member member) {
		return MemberResponse.builder()
			.id(member.getId())
			.username(member.getUsername())
			.nickname(member.getNickname())
			.password(member.getPassword())
			.phone(member.getPhone())
			.email(member.getEmail())
			.provider(member.getProvider())
			.providerId(member.getProviderId())
			.role(member.getRole())
			.build();
	}
}
