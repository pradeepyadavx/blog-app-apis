package com.pradeep.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pradeep.blog.entities.Comment;
import com.pradeep.blog.entities.Post;
import com.pradeep.blog.entities.User;
import com.pradeep.blog.exceptions.ResourseNotFoundException;
import com.pradeep.blog.payloads.CommentDto;
import com.pradeep.blog.repositories.CommentRepo;
import com.pradeep.blog.repositories.PostRepo;
import com.pradeep.blog.repositories.UserRepo;
import com.pradeep.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId,Integer userId) {
		
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourseNotFoundException("Post", "Post Id :", postId));
		
		User user= userRepo.findById(userId).orElseThrow(() -> new ResourseNotFoundException("User", "User Id :", userId));
		
		Comment comment= mapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
		comment.setUser(user);
		
		 Comment saveComment = commentRepo.save(comment);
		
		
		return this.mapper.map(saveComment,CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment= commentRepo.findById(commentId).orElseThrow(() -> new ResourseNotFoundException("Comment", "Comment Id", commentId));
		
		commentRepo.delete(comment);
	}

}
