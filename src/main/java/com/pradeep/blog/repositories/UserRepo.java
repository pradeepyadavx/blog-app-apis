package com.pradeep.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pradeep.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
