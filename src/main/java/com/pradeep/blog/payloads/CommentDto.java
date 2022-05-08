package com.pradeep.blog.payloads;

import com.pradeep.blog.entities.User;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentDto {
	
	private Integer commentId;
	
	private String comment;
	
	private UserDto user;

}
