package com.isfive.usearth.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/login")
public class HomeController {

	@GetMapping("view")
	public String login() {
		return "index";
	}

	@GetMapping
	@ResponseBody
	public String home() {
		return "home";
	}
}
