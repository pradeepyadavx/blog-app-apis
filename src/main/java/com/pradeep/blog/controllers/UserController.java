package com.pradeep.blog.controllers;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pradeep.blog.payloads.ApiResponse;
import com.pradeep.blog.payloads.UserDto;
import com.pradeep.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {


    @Autowired
    private UserService service;

    // POST save


    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {

        UserDto user = this.service.createUser(userDto);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


    //PUT update
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId) {

        UserDto user = this.service.updateUser(userDto, userId);

        return new ResponseEntity<UserDto>(user, HttpStatus.CREATED);


    }

    //GET getUsers
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {

        List<UserDto> users = this.service.getAllUsers();

        return new ResponseEntity<List<UserDto>>(users, HttpStatus.OK);

    }

    //Get Single User
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId) {
        UserDto user = this.service.getUserById(userId);

        //	return new ResponseEntity<UserDto>(user,HttpStatus.OK);

        return ResponseEntity.ok(user);
    }

    //DELETE delete users

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deletUser(@PathVariable("userId") Integer uId) {

        this.service.deleteUser(uId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Succcessfully ", true, new Date()), HttpStatus.OK);

    }

}
