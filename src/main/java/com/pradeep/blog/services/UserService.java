package com.pradeep.blog.services;

import java.util.List;

import com.pradeep.blog.payloads.UserDto;


public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, Integer userId);

    UserDto getUserById(Integer usedId);

    List<UserDto> getAllUsers();

    void deleteUser(Integer userId);
    
    UserDto registerNewUser(UserDto userDto);
}
