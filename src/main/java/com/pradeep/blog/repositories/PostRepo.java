package com.pradeep.blog.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pradeep.blog.entities.Category;
import com.pradeep.blog.entities.Post;
import com.pradeep.blog.entities.User;


@Repository
public interface PostRepo  extends JpaRepository<Post, Integer>{
	
	Page<Post> findByUser(User user,Pageable pageable);
	Page<Post> findByCategory(Category category,Pageable pageable);
	//List<Post> findByTitleContaining(String title);
	
	@Query("select p from Post p where p.title like :key")
	List<Post> serchByTitle(@Param("key")String  keyword);

}
