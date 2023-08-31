package com.isfive.usearth.domain.member.service;

import java.util.Map;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isfive.usearth.domain.member.dto.MemberResponse;
import com.isfive.usearth.domain.member.entity.Member;
import com.isfive.usearth.domain.member.entity.Role;
import com.isfive.usearth.domain.member.repository.MemberRepository;
import com.isfive.usearth.web.auth.dto.SignUpRegister;
import com.isfive.usearth.web.member.dto.UpdateRegister;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public MemberResponse createBy(SignUpRegister signUpRegister) {
		Member member = memberRepository.save(signUpRegister.toEntity().updateRole(Role.USER));
		member.encodePassword(passwordEncoder);
		return MemberResponse.fromEntity(member);
	}

	@Transactional
	public MemberResponse createByAttributes(Map<String, Object> attributes) {
		Member member = Member.builder()
			.username("sns-" + UUID.randomUUID())
			.email(attributes.get("email").toString())
			.provider(attributes.get("provider").toString())
			.providerId(attributes.get("id").toString())
			.role(Role.USER)
			.build();
		Member save = memberRepository.save(member);
		return MemberResponse.fromEntity(save);
	}

	@Transactional
	public MemberResponse updateRefreshToken(String username, String refreshToken) {
		Member member = memberRepository.findByUsernameOrThrow(username);
		member.updateRefreshToken(refreshToken);
		return MemberResponse.fromEntity(member);
	}

	@Transactional
	public MemberResponse updateRefreshTokenByEmail(String email, String refreshToken) {
		Member member = memberRepository.findByEmailOrThrow(email);
		member.updateRefreshToken(refreshToken);
		return MemberResponse.fromEntity(member);
	}

	@Transactional
	public MemberResponse updateUserInfo(String username, UserDetails userDetails) {
		Member member = memberRepository.findByUsernameOrThrow(username);
		member.updateInfo(userDetails);
		return MemberResponse.fromEntity(member);
	}

	@Transactional
	public MemberResponse updateUserInfoByUpdateRegister(String username, UpdateRegister updateRegister) {
		Member member = memberRepository.findByUsernameOrThrow(username);
		member.updateInfoByUpdateRegister(updateRegister);
		return MemberResponse.fromEntity(member);
	}

	@Transactional
	public void deleteBy(String username) {
		memberRepository.deleteByUsernameOrThrow(username);
	}

	public MemberResponse readByUsername(String username) {
		Member member = memberRepository.findByUsernameOrThrow(username);
		return MemberResponse.fromEntity(member);
	}

	public MemberResponse readByEmail(String email) {
		Member member = memberRepository.findByEmailOrThrow(email);
		return MemberResponse.fromEntity(member);
	}

	public boolean existBy(String username) {
		return memberRepository.existsByUsername(username);
	}

	public boolean existByEmail(String email) {
		return memberRepository.existsByEmail(email);
	}

	public boolean isUserAlright(Long memberId, String username) {
		Member member = memberRepository.findByUsernameOrThrow(username);
		return member.getId() == memberId;
	}
}
