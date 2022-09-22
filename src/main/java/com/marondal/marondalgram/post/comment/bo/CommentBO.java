package com.marondal.marondalgram.post.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marondal.marondalgram.post.comment.dao.CommentDAO;
import com.marondal.marondalgram.post.comment.model.Comment;

@Service
public class CommentBO {
	
	@Autowired
	private CommentDAO commentDAO;
	
	public int addComment(int userId, int postId, String content) {
		return commentDAO.insertComment(userId, postId, content);
	}
	
	// 게시글에 대응하는 댓글 리스트 가져오는 기능
	public List<Comment> getCommentList(int postId) {
		return commentDAO.selectCommentList(postId);
	}

}
