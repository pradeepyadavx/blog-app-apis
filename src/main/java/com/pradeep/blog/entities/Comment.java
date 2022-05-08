package com.pradeep.blog.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "comments")
@Setter
@Getter
public class Comment {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer commentId;
	
	
	private String comment;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Post post;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;

}
