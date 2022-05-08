package com.pradeep.blog.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pradeep.blog.payloads.ApiResponse;
import com.pradeep.blog.payloads.CommentDto;
import com.pradeep.blog.services.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/user/{userId}/post/{postId}/comments")
	public  ResponseEntity<CommentDto>  createComment(@RequestBody CommentDto commentDto, @PathVariable int userId,@PathVariable int postId) {
		
		CommentDto comment=commentService.createComment(commentDto, postId, userId);
		return new ResponseEntity<CommentDto>(comment,HttpStatus.CREATED);
				
	}
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse>  deleteComment(@PathVariable int commentId){
		
		commentService.deleteComment(commentId);
		
		return ResponseEntity.ok(new ApiResponse("Comment Deleted Successfully !!", true, new Date()));
		
	}

}
