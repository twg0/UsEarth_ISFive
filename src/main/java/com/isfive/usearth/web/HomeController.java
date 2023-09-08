package com.isfive.usearth.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/login")
public class HomeController {

	@GetMapping
	public String login() {
		return "index";
	}
}
