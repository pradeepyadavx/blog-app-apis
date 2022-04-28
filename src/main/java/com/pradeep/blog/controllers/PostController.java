package com.pradeep.blog.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pradeep.blog.config.ApplicationConstants;
import com.pradeep.blog.payloads.ApiResponse;
import com.pradeep.blog.payloads.PostDto;
import com.pradeep.blog.payloads.PostResponse;
import com.pradeep.blog.services.CategoryService;
import com.pradeep.blog.services.PostService;
import com.pradeep.blog.services.UserService;

@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	
	
	
	//POST create 
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){
		
		
		
		
		PostDto post = this.postService.createPost(postDto,userId,categoryId);
		
		return new ResponseEntity<PostDto>(post,HttpStatus.CREATED);
	}
	
	//PUT Update 
	
//	@PutMapping("/user/{userId}/category/{categoryId}/posts/{postId}")
//	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId, @PathVariable Integer userId, @PathVariable Integer categoryId){
//		PostDto post= this.postService.updatePost(postDto, postId,userId,categoryId);
//		return new ResponseEntity<PostDto>(post,HttpStatus.CREATED);
//	}
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
		PostDto post= this.postService.updatePostById(postDto, postId);
		return new ResponseEntity<PostDto>(post,HttpStatus.CREATED);
	}
	
	
	//DEL delete
	
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost (@PathVariable Integer postId){
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post  Deleted Succesfuly !!",true, new Date()),HttpStatus.OK);
	}
	
	//Get get
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPost(@PathVariable Integer postId){
		PostDto post = this.postService.getPost(postId);
		return ResponseEntity.ok(post);
	}
	
	//Get getAll
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getPosts(
			@RequestParam(value = "pageNumber" ,defaultValue = ApplicationConstants.PAGE_NUMBER , required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = ApplicationConstants.PAGE_SIZE, required = false) int pagesize,
			@RequestParam(value = "sortBy",defaultValue = ApplicationConstants.SORT_BY , required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = ApplicationConstants.SORT_DIR , required = false) String sortDir
			
			){
		PostResponse postresp= this.postService.getPosts(pageNumber,pagesize,sortBy,sortDir);
		
		return new ResponseEntity<PostResponse>(postresp,HttpStatus.OK);
	}
	
	
	//Get getAll post by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<PostResponse> getPostsByCategory( @PathVariable Integer categoryId,
			@RequestParam(value = "pageNumber" ,defaultValue = ApplicationConstants.PAGE_NUMBER , required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = ApplicationConstants.PAGE_SIZE, required = false) int pagesize
			
			){
	    PostResponse posts= this.postService.getByCategory(categoryId,pageNumber,pagesize);
		
		return ResponseEntity.ok(posts);
	}
	
	
	//Get getAll post by Users
		@GetMapping("/user/{userId}/posts")
		public ResponseEntity<PostResponse> getPostsByUser( @PathVariable Integer userId,
				@RequestParam(value = "pageNumber" ,defaultValue = ApplicationConstants.PAGE_NUMBER , required = false) int pageNumber,
				@RequestParam(value = "pageSize", defaultValue = ApplicationConstants.PAGE_SIZE, required = false) int pagesize
				){
			PostResponse posts= this.postService.getByUser(userId,pageNumber,pagesize);
			
			return ResponseEntity.ok(posts);
		}
		
		
		@GetMapping("/posts/search/{keyword}")
		public ResponseEntity<List<PostDto>> searchPostsByTitle(@PathVariable String keyword){
			List<PostDto> searchPosts = this.postService.searchPosts(keyword);
			
			return ResponseEntity.ok(searchPosts);
			
		}

	

}
