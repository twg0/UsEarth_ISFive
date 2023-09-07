package com.isfive.usearth.domain.auth.jwt.service;

import java.time.Duration;
import java.util.Optional;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.isfive.usearth.domain.member.entity.Member;
import com.isfive.usearth.domain.member.repository.MemberRepository;
import com.isfive.usearth.domain.utils.cookie.CookieUtils;
import com.isfive.usearth.domain.utils.jwt.JwtTokenUtils;
import com.isfive.usearth.exception.EntityNotFoundException;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {
	private final JwtTokenUtils tokenUtils;
	private final MemberRepository memberRepository;
	private final StringRedisTemplate redisTemplate;
	private final int AT_COOKIE_EXPIRE_SECONDS = 600; // Access Token 쿠키 10분
	private final int RT_COOKIE_EXPIRE_SECONDS = 3600; // Refresh Token 쿠키 1시간
	private String key = "refresh";

	@PostConstruct
	public void init() {
		log.info("Token Service init");
		redisTemplate.expire(key, Duration.ofHours(1));
	}

	// TODO 토큰 생성 후 쿠키 전송
	public String setTokenAndCookie(String email, HttpServletRequest request, HttpServletResponse response) {

		// 쿠키 비우기
		if (CookieUtils.getCookie(request,"serialAT").isPresent()) {
			CookieUtils.deleteCookie(request,response,"serialAT");
		}
		if (CookieUtils.getCookie(request,"serialRT").isPresent()) {
			CookieUtils.deleteCookie(request,response,"serialRT");
		}

		Optional<Member> optionalMember = memberRepository.findByEmail(email);

		if(optionalMember.isEmpty())
			return null;

		Member member = optionalMember.get();

		String accessToken = tokenUtils
			.createAccessToken(CustomUserDetails.fromEntity(member));
		String refreshToken = tokenUtils
			.createRefreshToken(CustomUserDetails.fromEntity(member));

		redisTemplate.opsForHash().put(key, email, refreshToken);

		String serialAT = CookieUtils.serialize(accessToken);
		String serialRT = CookieUtils.serialize(refreshToken);

		CookieUtils.addCookie(response,"serialAT", serialAT, AT_COOKIE_EXPIRE_SECONDS);
		CookieUtils.addCookie(response,"serialRT", serialRT, RT_COOKIE_EXPIRE_SECONDS);

		return accessToken;
	}

	// TODO 로그아웃
	public void logout(String email, HttpServletRequest request, HttpServletResponse response) {
		CookieUtils.deleteCookie(request,response,"serialAT");
		CookieUtils.deleteCookie(request,response,"serialRT");
		redisTemplate.opsForHash().delete(key, email);
	}
}
