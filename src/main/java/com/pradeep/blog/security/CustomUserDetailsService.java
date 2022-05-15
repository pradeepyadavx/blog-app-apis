package com.pradeep.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.pradeep.blog.entities.User;
import com.pradeep.blog.exceptions.ResourseNotFoundException;
import com.pradeep.blog.repositories.UserRepo;

@Component
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//load user by username  in our case its email;
		
		User user = userRepo.findByEmail(username).orElseThrow(() -> new ResourseNotFoundException("User", "Email id :"+username, 0));
		
		return user;
	}

}
