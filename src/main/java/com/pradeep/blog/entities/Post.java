package com.pradeep.blog.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name ="post")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	
	@Column(name = "post_title",length = 100, nullable = false)
	private String title;
	
	@Column(length = 1000)
	private String content;
	
	private String imageName;
	private Date addedDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@OneToMany(mappedBy = "post" , cascade = CascadeType.ALL,fetch = FetchType.EAGER )
	private Set<Comment> comments= new HashSet<>();
	
	
	
	

}
