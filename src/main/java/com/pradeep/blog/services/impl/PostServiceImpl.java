package com.pradeep.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pradeep.blog.entities.Category;
import com.pradeep.blog.entities.Post;
import com.pradeep.blog.entities.User;
import com.pradeep.blog.exceptions.ResourseNotFoundException;
import com.pradeep.blog.payloads.CategoryDto;
import com.pradeep.blog.payloads.PostDto;
import com.pradeep.blog.payloads.PostResponse;
import com.pradeep.blog.payloads.UserDto;
import com.pradeep.blog.repositories.PostRepo;
import com.pradeep.blog.services.CategoryService;
import com.pradeep.blog.services.PostService;
import com.pradeep.blog.services.UserService;

@Service
public class PostServiceImpl implements PostService {
	
	
	@Autowired
	private PostRepo postRepo;
	
	
	@Autowired
	private ModelMapper mapper;
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	
	private final String imageName="default.png";

	@Override
	public PostDto createPost(PostDto postDto,Integer userId, Integer categoryId) {
		
		UserDto user= this.userService.getUserById(userId);
		CategoryDto category= this.categoryService.getCategory(categoryId);
		
		postDto.setImageName(imageName);
		postDto.setAddedDate(new Date());
		postDto.setUser(user);
		postDto.setCategory(category);
		
		
		Post post= this.mapper.map(postDto, Post.class);
		
		Post postResp= this.postRepo.save(post);
		
		return this.mapper.map(postResp, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId,Integer userId, Integer categoryId) {
		UserDto user= this.userService.getUserById(userId);
		CategoryDto category=this.categoryService.getCategory(categoryId);
		
		Post post= this.postRepo.findById(postId).orElseThrow(() -> new ResourseNotFoundException("Post", "postId", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
	//	post.setImageName(postDto.getImageName());
	//	post.setAddedDate(postDto.getAddedDate());
		
		
		
		
		post.setCategory(this.mapper.map(category,Category.class));
		post.setUser(this.mapper.map(user, User.class));
		
		Post updatedPost= this.postRepo.save(post);
		
		return this.mapper.map(updatedPost, PostDto.class);
	}
	
	@Override
	public PostDto updatePostById(PostDto postDto, Integer postId) {
		
		
		Post post= this.postRepo.findById(postId).orElseThrow(() -> new ResourseNotFoundException("Post", "postId", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
	//	post.setImageName(postDto.getImageName());
	//	post.setAddedDate(postDto.getAddedDate());
		
		
		
		
		Post updatedPost= this.postRepo.save(post);
		
		return this.mapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		
		Post post  = this.postRepo.findById(postId).orElseThrow(() -> new ResourseNotFoundException("Post", "postId", postId));
		
		this.postRepo.delete(post);

	}

	@Override
	public PostDto getPost(Integer postId) {
		Post post= this.postRepo.findById(postId).orElseThrow(() -> new ResourseNotFoundException("Post", "postId", postId));
		
		return this.mapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse getPosts(int pageNumber,int pageSize,String sortBy,String sortDir) {
		
		
		
		Sort sort=(sortDir.equalsIgnoreCase("asc"))? Sort.by(sortBy) : Sort.by(sortBy).descending();

		Pageable page= PageRequest.of(pageNumber, pageSize,sort);
		
		Page<Post> pagePost = this.postRepo.findAll(page);
		
		List<Post> posts = pagePost.getContent();
		
		
	List<PostDto> postsDto=	posts.stream().map(post -> this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
		
	PostResponse postsResp= new PostResponse();
	
	postsResp.setPosts(postsDto);
	postsResp.setPageNumber(pagePost.getNumber());
	postsResp.setPagesize(pagePost.getSize());
	postsResp.setTotalElements(pagePost.getTotalElements());
	postsResp.setTotalPages(pagePost.getTotalPages());
	postsResp.setLastPage(pagePost.isLast());
	
	return postsResp;
	}

	@Override
	public PostResponse getByCategory(Integer categoryId,int pageNumber,int pageSize) {
		CategoryDto categoryDto= this.categoryService.getCategory(categoryId);
		
		Pageable page=PageRequest.of(pageNumber, pageSize);
		
		
		
	  Page<Post> findByCategory = this.postRepo.findByCategory(this.mapper.map(categoryDto, Category.class),page);
		
		
		List<Post> posts = findByCategory.getContent();
		
		
		List<PostDto> postsDtos=posts.stream().map( post -> this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postsResp= new PostResponse();
		
		postsResp.setPosts(postsDtos);
		postsResp.setPageNumber(findByCategory.getNumber());
		postsResp.setPagesize(findByCategory.getSize());
		postsResp.setTotalElements(findByCategory.getTotalElements());
		postsResp.setTotalPages(findByCategory.getTotalPages());
		postsResp.setLastPage(findByCategory.isLast());
		
		return postsResp;
	}

	@Override
	public PostResponse getByUser(Integer userId,int pageNumber,int pageSize ) {
		UserDto userDto= this.userService.getUserById(userId);
		
		Pageable page= PageRequest.of(pageNumber, pageSize);
		
		Page<Post> findbyUser=  this.postRepo.findByUser(this.mapper.map(userDto, User.class),page);
		
		List<Post> posts = findbyUser.getContent();
		
		List<PostDto> postsDto= posts.stream().map(post -> this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postsResp= new PostResponse();
		
		postsResp.setPosts(postsDto);
		postsResp.setPageNumber(findbyUser.getNumber());
		postsResp.setPagesize(findbyUser.getSize());
		postsResp.setTotalElements(findbyUser.getTotalElements());
		postsResp.setTotalPages(findbyUser.getTotalPages());
		postsResp.setLastPage(findbyUser.isLast());
		
		
		return postsResp;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> findByTitleContaining = this.postRepo.serchByTitle("%"+keyword+"%");
		
		List<PostDto> collect = findByTitleContaining.stream().map(post -> this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		
		return  collect;
	}

	

}
