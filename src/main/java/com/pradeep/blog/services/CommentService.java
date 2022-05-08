package com.pradeep.blog.services;

import com.pradeep.blog.payloads.CommentDto;

public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto,Integer postId,Integer userId);
	void deleteComment(Integer commentId);

}
