package com.pradeep.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pradeep.blog.exceptions.ResourseNotFoundException;
import com.pradeep.blog.payloads.JwtAuthRequest;
import com.pradeep.blog.payloads.JwtAuthResponse;
import com.pradeep.blog.payloads.UserDto;
import com.pradeep.blog.security.JwtTokenHelper;
import com.pradeep.blog.services.UserService;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
	
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@Autowired
	private UserService userService;
	
	
	
	@PostMapping("login")
	public ResponseEntity<JwtAuthResponse>  createToken(@RequestBody JwtAuthRequest request){
		
		authenticate(request.getUsername(),request.getPassword());
		
		UserDetails userDetails= this.userDetailsService.loadUserByUsername(request.getUsername());
		
		String token = this.jwtTokenHelper.generateToken(userDetails);
		
		JwtAuthResponse response= new JwtAuthResponse();
		response.setToken(token);
		
		return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
		
		
	}


	private void authenticate(String username, String password) {
		UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(username, password);
		
		try {
			this.authenticationManager.authenticate(authenticationToken);
		} catch (Exception e) {
			throw new  ResourseNotFoundException("Authentication","username and password Invalid",0);
		}
		
	}
	

	@PostMapping("register")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
		
		UserDto registerNewUser = this.userService.registerNewUser(userDto);
		
		return new ResponseEntity<>(registerNewUser,HttpStatus.CREATED);
		
	}

}
