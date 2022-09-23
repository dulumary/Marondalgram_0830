package com.marondal.marondalgram.post.model;

import java.util.List;

import com.marondal.marondalgram.post.comment.model.CommentDetail;
import com.marondal.marondalgram.user.model.User;

public class PostDetail {
	
	private Post post;
	private User user;
	private int likeCount;
	private boolean isLike;
	private List<CommentDetail> commentList;
	
	public List<CommentDetail> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<CommentDetail> commentList) {
		this.commentList = commentList;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public boolean isLike() {
		return isLike;
	}
	public void setLike(boolean isLike) {
		this.isLike = isLike;
	}
	
	
}
