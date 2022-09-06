package com.marondal.marondalgram.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	
	@GetMapping("/signout")
	public String signout(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		// 로그인 정보 제거
		// 세션에 로그인 사용자 정보 제거 
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		
		return "redirect:/user/signin/view";
		
	}
	

}
