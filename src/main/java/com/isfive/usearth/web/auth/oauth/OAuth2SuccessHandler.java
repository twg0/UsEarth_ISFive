package com.isfive.usearth.web.auth.oauth;

import com.isfive.usearth.domain.auth.jwt.service.TokenService;
import com.isfive.usearth.domain.member.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
// OAuth2 통신이 성공적으로 끝났을 때, 사용하는 클래스
// JWT를 활용한 인증 구현하고 있기 때문에
// ID Provider에게 받은 정보를 바탕으로 JWT를 발급하는 역할을 하는 용도
// JWT를 발급하고 클라이언트가 저장할 수 있도록 특정 URL로 리다이렉트 시키자.
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private final MemberService memberService;
	private final TokenService tokenService;

	@Override
	public void onAuthenticationSuccess(
		HttpServletRequest request,
		HttpServletResponse response,
		Authentication authentication
	) throws IOException, ServletException {
		log.info("onAuthenticationSuccess 진입");
		// OAuth2UserServiceImpl에서 반환한 DefaultOAuth2User
		// 가 저장된다.
		OAuth2User oAuth2User
			= (OAuth2User)authentication.getPrincipal();

		Map<String, Object> attributes = oAuth2User.getAttributes();

		String email = attributes.get("email").toString();

		if (!memberService.existByEmail(email)) {
			memberService.createByAttributes(attributes);
		}

		tokenService.setTokenAndCookie(email, request, response);
	}

}
