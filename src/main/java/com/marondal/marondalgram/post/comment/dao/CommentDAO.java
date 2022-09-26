package com.marondal.marondalgram.post.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.marondal.marondalgram.post.comment.model.Comment;

@Repository
public interface CommentDAO {
	
	public int insertComment(
			@Param("userId") int userId
			, @Param("postId") int postId
			, @Param("content")	 String content);

	public List<Comment> selectCommentList(@Param("postId") int postId);
	
	public int deleteCommentByPostId(@Param("postId") int postId);
}
