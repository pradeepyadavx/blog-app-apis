package com.pradeep.blog;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pradeep.blog.config.ApplicationConstants;
import com.pradeep.blog.entities.Role;
import com.pradeep.blog.repositories.RoleRepo;


@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;

    public static void main(String[] args) {
        SpringApplication.run(BlogAppApisApplication.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		System.out.println(passwordEncoder.encode("pradeep"));
		
		Role role= new Role();
		role.setId(ApplicationConstants.ADMIN_USER);
		role.setName("ROLE_ADMIN");
		
		Role role2= new Role();
		
		role2.setId(ApplicationConstants.NORMAL_USER);
		role2.setName("ROLE_NORMAL");
		
		
		List<Role> roles= new ArrayList<>();
		roles.add(role);
		roles.add(role2);
		
		List<Role> saveAll = roleRepo.saveAll(roles);
		
		saveAll.forEach( x -> System.out.println(x.getName()));
		
		
		
		
		
		
	}

}
