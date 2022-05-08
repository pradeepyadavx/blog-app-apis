package com.pradeep.blog.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pradeep.blog.entities.User;
import com.pradeep.blog.exceptions.ResourseNotFoundException;
import com.pradeep.blog.payloads.UserDto;
import com.pradeep.blog.repositories.UserRepo;
import com.pradeep.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepo userRepo;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = this.userRepo.save(dtoToUser(userDto));


        return userToDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {

        User user = userRepo.findById(userId).orElseThrow(() -> new ResourseNotFoundException("User", "Id", userId));

        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());

        User updatedUser = this.userRepo.save(user);

        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer usedId) {
        User user = this.userRepo.findById(usedId).orElseThrow(() -> new ResourseNotFoundException("User", "id", usedId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();

        List<UserDto> usersDTo = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return usersDTo;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourseNotFoundException("User", "Id", userId));

        this.userRepo.delete(user);
        
       // this.userRepo.deleteById(userId);

    }

    private User dtoToUser(UserDto userDto) {

//		User user= new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());


        User user = this.modelMapper.map(userDto, User.class);
        return user;
    }

    private UserDto userToDto(User user) {
//		UserDto userDto= new UserDto();
//		
//		
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
    	//user.setPassword(null);

        UserDto userDto = this.modelMapper.map(user, UserDto.class);


        return userDto;

    }


}
