package com.pradeep.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pradeep.blog.entities.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment,Integer> {

}
