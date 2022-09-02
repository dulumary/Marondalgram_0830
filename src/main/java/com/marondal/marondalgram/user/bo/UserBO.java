package com.marondal.marondalgram.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marondal.marondalgram.common.EncryptUtils;
import com.marondal.marondalgram.user.dao.UserDAO;
import com.marondal.marondalgram.user.model.User;

@Service
public class UserBO {
	
	@Autowired
	private UserDAO userDAO;
	
	
	public int addUser(String loginId, String password, String name, String email) {
		
		String encryptPassword = EncryptUtils.md5(password);
		
		return userDAO.insertUser(loginId, encryptPassword, name, email);
	}
	
	public boolean isDuplicate(String loginId) {
		
		int count = userDAO.selectCountLoginID(loginId);
		
//		if(count == 0) {
//			return false;
//		} else {
//			return true;
//		}
		
		return count != 0;
		
	}
	
	// loginId 와 password 가 일치하는 사용자 정보를 돌려주는 기능
	
	public User getUser(String loginId, String password) {
		
		String encryptPassword = EncryptUtils.md5(password);
		
		return userDAO.selectUser(loginId, encryptPassword);
	}
	

}
