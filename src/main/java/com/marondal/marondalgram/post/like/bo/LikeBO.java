package com.marondal.marondalgram.post.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marondal.marondalgram.post.like.dao.LikeDAO;

@Service
public class LikeBO {
	
	@Autowired
	private LikeDAO likeDAO;
	
	public int addLike(int userId, int postId) {
		
		return likeDAO.insertLike(userId, postId);
		
	}
	
	public int deleteLike(int userId, int postId) {
		return likeDAO.deleteLike(userId, postId);
	}
	
	// 게시글별 좋아요 개수 얻는 기능
	public int getLikeCount(int postId) {
		
		return likeDAO.selectCountLike(postId);
	}
	
	
	// 특정사용자가 특정 게시글에 좋아요 여부 확인 기능
	public boolean isLike(int userId, int postId) {
		int count = likeDAO.selectCountLikeByUserId(userId, postId);
		
		// count == 0 이면 좋아요 하지 않았다. 
		if(count == 0) {
			return false;
		} else {
			return true;
		}
	}

	// 특정 게시물의 좋아요 삭제 
	public int deleteLikeByPostId(int postId) {
		return likeDAO.deleteLikeByPostId(postId);
	}
	
}
