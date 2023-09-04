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

	@Override
	protected void doFilterInternal(
		HttpServletRequest request,
		HttpServletResponse response,
		FilterChain filterChain
	) throws ServletException, IOException {

		Optional<Cookie> serialAT = CookieUtils.getCookie(request, "serialAT");
		Optional<Cookie> serialRT = CookieUtils.getCookie(request, "serialRT");

		if (serialAT.isEmpty() && serialRT.isEmpty()) {
			log.info("uri {}", request.getRequestURI());
			filterChain.doFilter(request, response);
			return;
		}

		if (serialAT.isEmpty())
			throw new AuthException(ErrorCode.BAD_REQUEST_COOKIE);

		String token = CookieUtils.deserialize(serialAT.get(), String.class);

		String validateResult = jwtTokenUtils.validateAccessToken(token);

		if (validateResult == null) {
			log.warn("jwt validation failed");
			throw new InvalidValueException(ErrorCode.TOKEN_INVALID);
		} else {

			if (validateResult.equals("need refresh")) {
				CookieUtils.deleteCookie(request,response,"serialAT");
				String refreshToken = CookieUtils.deserialize(serialRT.get(), String.class);
				token = jwtTokenUtils.validateRefreshToken(refreshToken);
				if (token == null) {
					CookieUtils.deleteCookie(request,response,"serialRT");
					response.sendError(401);
					filterChain.doFilter(request, response);
					return;
				}
				CookieUtils.addCookie(response,"serialAT",token,60);
				CookieUtils.addCookie(response,"serialRT",refreshToken,300);
			}

			SecurityContext context
				= SecurityContextHolder.createEmptyContext();
			// JWT에서 사용자 정보 가져오기
			UserDetails userDetails = jwtTokenUtils
				.getUserDetails(token);
			// 사용자 인증 정보 생성
			AbstractAuthenticationToken authenticationToken
				= new UsernamePasswordAuthenticationToken(
				CustomUserDetails.builder()
					.username(((CustomUserDetails)userDetails).getUsername())
					.build(),
				token, userDetails.getAuthorities()
			);
			// SecurityContext에 사용자 정보 설정
			context.setAuthentication(authenticationToken);
			// SecurityContextHolder에 SecurityContext 설정
			SecurityContextHolder.setContext(context);
			log.info("set security context with jwt");
		}
		filterChain.doFilter(request, response);
	}
}
