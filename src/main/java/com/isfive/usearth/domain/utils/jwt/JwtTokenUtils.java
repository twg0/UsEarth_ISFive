package com.isfive.usearth.domain.utils.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.isfive.usearth.domain.auth.jwt.service.CustomUserDetails;
import com.isfive.usearth.domain.auth.jwt.service.JpaUserDetailsManager;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtTokenUtils {
	private final Key signingKey;
	private final Key refreshKey;
	private final JwtParser jwtParser;
	private final JpaUserDetailsManager userDetailsManager;
	public final Long ACCESS_TOKEN_EXPIRATION_TIME;
	public final Long REFRESH_TOKEN_EXPIRATION_TIME;

	public JwtTokenUtils(
		@Value("${security.jwt.base-64-secret}")
		String jwtSecret,
		@Value("${security.jwt.refresh-secret}")
		String refreshSecret,
		@Value("${security.jwt.access-expiration-time}") Long accessExpirationTime,
		@Value("${security.jwt.refresh-expiration-time}") Long refreshExpirationTime,
		JpaUserDetailsManager userDetailsManager
	) {
		this.signingKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
		// jwt 번역기 만들기
		this.jwtParser = Jwts.parserBuilder()
			.setSigningKey(this.signingKey)
			.build();
		this.ACCESS_TOKEN_EXPIRATION_TIME = accessExpirationTime;
		this.REFRESH_TOKEN_EXPIRATION_TIME = refreshExpirationTime;
		this.userDetailsManager = userDetailsManager;
		this.refreshKey = Keys.hmacShaKeyFor(refreshSecret.getBytes());
	}

	// 1. JWT가 유효한지 판단하는 메소드
	//    jjwt 라이브러리에서는 JWT를 해석하는 과정에서
	//    유효하지 않으면 예외가 발생
	public boolean validate(String token) {
		try {
			// 정당한 JWT면 true,
			// parseClaimsJws: 암호화된 JWT를 해석하기 위한 메소드
			jwtParser.parseClaimsJws(token);
			return true;
			// 정당하지 않은 JWT면 false
		} catch (Exception e) {
			log.warn("invalid jwt: {}", e.getClass());
			return false;
		}
	}
	public String validateAccessToken(String token) {
		try {
			// 검증
			Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token);

			// access 토큰의 만료시간이 지났을경우
			if (!claims.getBody().getExpiration().after(new Date())) {
				return "need refresh";
			}
			return "ok";
		} catch (Exception e) {
			log.warn("invalid jwt: {}", e.getClass());
			return null;
		}
	}

	public String validateRefreshToken(String token) {
		try {
			// 검증
			Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(refreshKey).build().parseClaimsJws(token);

			// refresh 토큰의 만료시간이 지나지 않았을 경우, 새로운 access 토큰을 생성
			if (!claims.getBody().getExpiration().before(new Date())) {
				return createAccessToken((CustomUserDetails)getUserDetails(token));
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	// JWT를 인자로 받고, 그 JWT를 해석해서
	// 사용자 정보를 회수하는 메소드
	public Claims parseClaims(String token) {
		return jwtParser
			.parseClaimsJws(token)
			.getBody();
	}

	public UserDetails getUserDetails(String token) {
		return userDetailsManager.loadUserByUsername(this.parseClaims(token).getSubject());
	}

	// 주어진 사용자 정보를 바탕으로 JWT를 문자열로 생성
	public String generateToken(CustomUserDetails userDetails, long expirationTime, Key key) {
		// Claims: JWT에 담기는 정보의 단위를 Claim이라 부른다.
		//         Claims는 Claim들을 담기위한 Map의 상속 interface
		Claims jwtClaims = Jwts.claims()
			// 사용자 정보 등록
			.setSubject(userDetails.getEmail())
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + expirationTime));

		return Jwts.builder()
			.setClaims(jwtClaims)
			.signWith(key)
			.compact();
	}

	public String createAccessToken(CustomUserDetails userDetails) {
		return generateToken(userDetails, ACCESS_TOKEN_EXPIRATION_TIME, signingKey);
	}

	public String createRefreshToken(CustomUserDetails userDetails) {
		return generateToken(userDetails, REFRESH_TOKEN_EXPIRATION_TIME, refreshKey);
	}
}
