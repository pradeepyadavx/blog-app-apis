package com.pradeep.blog.services;

import java.util.List;

import com.pradeep.blog.payloads.PostDto;
import com.pradeep.blog.payloads.PostResponse;

public interface PostService {
	
	//create 
	
	PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);
	
	//update
	
	PostDto updatePost(PostDto postDto, Integer postId, Integer userId, Integer categoryId);
	
	//Update
	
	PostDto updatePostById(PostDto postDto, Integer postId);

	
	// delete
	
	void deletePost(Integer postId);
	
	
	// get 
	
	PostDto getPost(Integer postId);

	
	// get all
	
	PostResponse getPosts(int pageNumber,int pageSize,String sortBy,String sortDir);
	
	//get All Post by category Id
	
	PostResponse getByCategory(Integer categoryId,int pageNumber,int pageSize );
	
	
	// get All Posts by User Id
	
	PostResponse getByUser(Integer userId,int pageNumber,int pageSize );
	
	// Search Post
	List<PostDto> searchPosts(String keyword);


}
