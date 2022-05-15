 package com.pradeep.blog.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pradeep.blog.services.UserService;
import com.pradeep.blog.services.impl.UserServiceImpl;

@Configuration
public class UserConfig {


//	@Bean
//	public UserService userService() {
//		
//		return new UserServiceImpl();
//	}

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
