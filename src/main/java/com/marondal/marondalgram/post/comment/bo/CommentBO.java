package com.marondal.marondalgram.post.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marondal.marondalgram.post.comment.dao.CommentDAO;
import com.marondal.marondalgram.post.comment.model.Comment;
import com.marondal.marondalgram.post.comment.model.CommentDetail;
import com.marondal.marondalgram.user.bo.UserBO;
import com.marondal.marondalgram.user.model.User;

@Service
public class CommentBO {
	
	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private UserBO userBO;
	
	public int addComment(int userId, int postId, String content) {
		return commentDAO.insertComment(userId, postId, content);
	}
	
	// 게시글에 대응하는 댓글 리스트 가져오는 기능
	public List<CommentDetail> getCommentList(int postId) {
		List<Comment> commentList = commentDAO.selectCommentList(postId);
		List<CommentDetail> commentDetailList = new ArrayList<>();
		
		for(Comment comment : commentList) {
			
			User user = userBO.getUserById(comment.getUserId());
			
			CommentDetail commentDetail = new CommentDetail();
			commentDetail.setComment(comment);
			commentDetail.setUser(user);
			
			commentDetailList.add(commentDetail);
		}
		
		return commentDetailList;
	}

}
