package com.marondal.marondalgram.post.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.marondal.marondalgram.common.FileManagerService;
import com.marondal.marondalgram.post.comment.bo.CommentBO;
import com.marondal.marondalgram.post.comment.model.CommentDetail;
import com.marondal.marondalgram.post.dao.PostDAO;
import com.marondal.marondalgram.post.like.bo.LikeBO;
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
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private CommentBO commentBO;
	
	// 게시글 정보를 전달 받아서 저장하는 기능
	public int addPost(int userId, String content, MultipartFile file) {
		
		String imagePath = FileManagerService.saveFile(userId, file);
		
		// 파일 업로드가 문제가 있으면, 인서트를 진행하지 않고, 입력이 진행되지 않는다는 return 값을 만들어준다
		if(imagePath == null) {
			return 0;
		}
		
		return postDAO.insertPost(userId, content, imagePath);	
	}
	
	public List<PostDetail> getPostList(int loginUserId) {
		
		// 게시글 하나당 작성자 정보 를 조합하는 과정 
		List<Post> postList = postDAO.selectPostList();
		
		List<PostDetail> postDetailList = new ArrayList<>();
		
		for(Post post : postList) {
			
			int userId = post.getUserId();
			User user = userBO.getUserById(userId);
			
			int likeCount = likeBO.getLikeCount(post.getId());
			boolean isLike = likeBO.isLike(loginUserId, post.getId());
			
			List<CommentDetail> commentList = commentBO.getCommentList(post.getId());
			
			PostDetail postDetail = new PostDetail();
			postDetail.setPost(post);
			postDetail.setUser(user);
			postDetail.setLikeCount(likeCount);
			postDetail.setLike(isLike);
			postDetail.setCommentList(commentList);
			
			postDetailList.add(postDetail);
		}
		
		
		return postDetailList;
	}
	
	
	public int deletePost(int postId, int userId) {
		
		Post post = postDAO.selectPostByIdAndUserId(postId, userId);
		
		if(post == null) {
			return 0;
		}
		
		// 게시글과 연결된 파일 삭제 
		FileManagerService.removeFile(post.getImagePath());
		
		// 좋아요 삭제
		likeBO.deleteLikeByPostId(postId);
		// 댓글 삭제
		commentBO.deleteCommentByPostId(postId);
		
		return postDAO.deletePost(postId);
	}
	
	

}
