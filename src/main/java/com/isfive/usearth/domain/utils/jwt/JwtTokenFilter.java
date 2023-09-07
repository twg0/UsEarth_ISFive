package com.isfive.usearth.domain.utils.jwt;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.isfive.usearth.domain.auth.jwt.service.CustomUserDetails;
import com.isfive.usearth.domain.auth.jwt.service.TokenService;
import com.isfive.usearth.domain.utils.cookie.CookieUtils;
import com.isfive.usearth.exception.AuthException;
import com.isfive.usearth.exception.ErrorCode;
import com.isfive.usearth.exception.InvalidValueException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
	private final JwtTokenUtils jwtTokenUtils;
	private final TokenService tokenService;

	@Override
	protected void doFilterInternal(
		HttpServletRequest request,
		HttpServletResponse response,
		FilterChain filterChain
	) throws ServletException, IOException {

		Optional<Cookie> serialAT = CookieUtils.getCookie(request, "serialAT");
		Optional<Cookie> serialRT = CookieUtils.getCookie(request, "serialRT");

		if ((serialAT.isEmpty() && serialRT.isEmpty()) || request.getRequestURI().equals("/favicon.ico")) {
			log.info("no Cookie uri = {}", request.getRequestURI());
			filterChain.doFilter(request, response);
			return;
		}

		String token = null;

		// AT 쿠키가 만료되었다면
		if (serialAT.isEmpty()) {
			log.info("AT 쿠키 만료");
			String oldRefreshToken = CookieUtils.deserialize(serialRT.get(), String.class);
			String email = jwtTokenUtils.validateRefreshToken(oldRefreshToken);

			// Refresh Token validate 실패시
			if(email == null) {
				log.info("Refresh Token validate 실패");
				CookieUtils.deleteCookie(request,response,"serialRT");
				response.sendError(401);
				filterChain.doFilter(request, response);
				return;
			}// Token 재발급 필요시
			else {
				log.info("Token 재발급 필요");
				token = tokenService.setTokenAndCookie(email,request, response);
			}
		}// Access Token 검증
		else {
			log.info("Access Token 검증");
			String oldAccessToken = CookieUtils.deserialize(serialAT.get(), String.class);
			String email = jwtTokenUtils.validateAccessToken(oldAccessToken);
			/*
			Access Token validate 실패시
			쿠키 삭제 및 에러 전송
			 */
			if(email == null) {
				log.info("Access Token validate 실패");
				CookieUtils.deleteCookie(request,response,"serialAT");
				response.sendError(401);
				filterChain.doFilter(request, response);
				return;
			}
			// Token 재발급 필요시
			else if (!email.equals("ok")) {
				log.info("Token 재발급 필요");
				token = tokenService.setTokenAndCookie(email, request, response);
			} else {
				log.info("Token 재발급 필요 없음");
				token = oldAccessToken;
			}
		}

		if (token == null) {
			log.info("token == null 다음 filter");
			filterChain.doFilter(request, response);
			return;
		}

		SecurityContext context
			= SecurityContextHolder.createEmptyContext();

		// JWT에서 사용자 정보 가져오기
		UserDetails userDetails = jwtTokenUtils
			.getUserDetails(token);

		if(userDetails == null) {
			log.info("userDetails == null 다음 filter");
			filterChain.doFilter(request, response);
			return;
		}
		// 사용자 인증 정보 생성
		AbstractAuthenticationToken authenticationToken
			= new UsernamePasswordAuthenticationToken(
			CustomUserDetails.builder()
				.email(((CustomUserDetails)userDetails).getEmail())
				.build(),
			token, userDetails.getAuthorities()
		);
		log.info("((CustomUserDetails)userDetails).getEmail() = {}",((CustomUserDetails)userDetails).getEmail());

		// SecurityContext에 사용자 정보 설정
		context.setAuthentication(authenticationToken);

		// SecurityContextHolder에 SecurityContext 설정
		SecurityContextHolder.setContext(context);

		log.info("set security context with jwt");

		filterChain.doFilter(request, response);
	}
}
