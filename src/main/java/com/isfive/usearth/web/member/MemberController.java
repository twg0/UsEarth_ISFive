package com.isfive.usearth.web.member;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/members")
public class MemberController {

	@GetMapping("/my-page")
	public String getJson(Authentication authentication) {

		return "my-page";
	}
}
