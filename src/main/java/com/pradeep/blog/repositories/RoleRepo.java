package com.pradeep.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pradeep.blog.entities.Role;


public interface RoleRepo  extends JpaRepository<Role, Integer>{

}
