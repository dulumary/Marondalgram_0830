package com.marondal.marondalgram.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marondal.marondalgram.user.bo.UserBO;
import com.marondal.marondalgram.user.model.User;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	private UserBO userBO;
	
	// 회원 가입 api
	@PostMapping("/signup")
	public Map<String, String> signup(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, @RequestParam("name") String name
			, @RequestParam("email") String email) {
		
		int count = userBO.addUser(loginId, password, name, email);
		
		Map<String, String> result = new HashMap<>();
		
		if(count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
		
	}
	
	
	// id 중복 확인 api
	@GetMapping("/duplicate_id")
	public Map<String, Boolean> isDuplicate(@RequestParam("loginId") String loginId) {
		
		boolean isDuplicate = userBO.isDuplicate(loginId);
		
//		중복시	{"id_duplicate":true}
//		중복이 아닌경우	{"id_duplicate":false}
		
		Map<String, Boolean> result = new HashMap<>();
		
		if(isDuplicate) {
			result.put("id_duplicate", true);
		} else {
			result.put("id_duplicate", false);
		}
		
		return result;
	}
	
	
	// 로그인 api
	@PostMapping("/signin")
	public Map<String, String> signin(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, HttpServletRequest request) {
		
		User user = userBO.getUser(loginId, password);
		
		Map<String, String> result = new HashMap<>();
		
		if(user != null) {  // 로그인 성공
			HttpSession session = request.getSession();
			// user name 
			// user id
			session.setAttribute("userId", user.getId());
			session.setAttribute("userName", user.getName());
			
			result.put("result", "success");
			
		} else { // 로그인 실패
			result.put("result", "fail");
		}
		
		return result;
		
	}
	
	

}
