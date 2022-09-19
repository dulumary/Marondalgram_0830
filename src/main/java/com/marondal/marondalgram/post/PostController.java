package com.marondal.marondalgram.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.marondal.marondalgram.post.bo.PostBO;
import com.marondal.marondalgram.post.model.Post;

@Controller
public class PostController {
	
	@Autowired
	private PostBO postBO;
	
	@GetMapping("/post/timeline/view")
	public String timeline(Model model) {
		
		List<Post> postList = postBO.getPostList();
		
		model.addAttribute("postList", postList);
		
		return "post/timeline";
	}

}
