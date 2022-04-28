package com.pradeep.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pradeep.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
