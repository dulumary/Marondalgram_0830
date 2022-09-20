package com.marondal.marondalgram.post.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.marondal.marondalgram.common.FileManagerService;
import com.marondal.marondalgram.post.dao.PostDAO;
import com.marondal.marondalgram.post.model.Post;
import com.marondal.marondalgram.post.model.PostDetail;
import com.marondal.marondalgram.user.bo.UserBO;
import com.marondal.marondalgram.user.model.User;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private UserBO userBO;
	
	// 게시글 정보를 전달 받아서 저장하는 기능
	public int addPost(int userId, String content, MultipartFile file) {
		
		String imagePath = FileManagerService.saveFile(userId, file);
		
		// 파일 업로드가 문제가 있으면, 인서트를 진행하지 않고, 입력이 진행되지 않는다는 return 값을 만들어준다
		if(imagePath == null) {
			return 0;
		}
		
		return postDAO.insertPost(userId, content, imagePath);	
	}
	
	public List<PostDetail> getPostList() {
		
		// 게시글 하나당 작성자 정보 를 조합하는 과정 
		List<Post> postList = postDAO.selectPostList();
		
		List<PostDetail> postDetailList = new ArrayList<>();
		
		for(Post post : postList) {
			
			int userId = post.getUserId();
			User user = userBO.getUserById(userId);
			
			PostDetail postDetail = new PostDetail();
			postDetail.setPost(post);
			postDetail.setUser(user);
			
			postDetailList.add(postDetail);
		}
		
		
		return postDetailList;
	}
	
	
	

}
