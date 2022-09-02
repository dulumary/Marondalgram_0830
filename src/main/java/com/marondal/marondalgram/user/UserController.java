package com.marondal.marondalgram.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@GetMapping("/signup/view")
	public String signup() {
		return "user/signup";
	}
	
	@GetMapping("/signin/view")
	public String signin() {
		return "user/signin";
	}

}
