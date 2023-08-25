package com.isfive.usearth.web.member;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
public class MemberController {

	@GetMapping("/my-page")
	public String getJson(Authentication authentication) {

		return "my-page";
	}
}
