package com.isfive.usearth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.isfive.usearth.domain.auth.oauth.service.OAuth2UserServiceImpl;
import com.isfive.usearth.domain.utils.jwt.JwtTokenFilter;
import com.isfive.usearth.web.auth.oauth.OAuth2SuccessHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final JwtTokenFilter jwtTokenFilter;
	private final OAuth2SuccessHandler oAuth2SuccessHandler;
	private final OAuth2UserServiceImpl oAuth2UserServiceImpl;

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web.ignoring()
				.requestMatchers(toH2Console());
	}

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(authHttp -> authHttp
				.requestMatchers(
					new AntPathRequestMatcher("/token/**"),
					new AntPathRequestMatcher("/views/**"),
					new AntPathRequestMatcher("/login/**"),
					new AntPathRequestMatcher("/")
				).permitAll()
				.requestMatchers(
					new AntPathRequestMatcher("/members/my-page"),
						new AntPathRequestMatcher("/projects", "/projects/{projectId}")
				).hasRole("USER")
				.anyRequest()
				.authenticated()
			)
			.oauth2Login(oauth2Login -> oauth2Login
				.loginPage("/login")
				.successHandler(oAuth2SuccessHandler)
				.userInfoEndpoint(userInfo -> userInfo
					.userService(oAuth2UserServiceImpl)
				)
			)
			.sessionManagement(
				sessionManagement -> sessionManagement
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(jwtTokenFilter, AuthorizationFilter.class);

		return http.build();
	}

}
