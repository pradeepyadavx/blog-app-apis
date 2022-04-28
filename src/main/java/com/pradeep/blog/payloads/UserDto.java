package com.pradeep.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDto {


    private int id;

    @NotEmpty
    @Size(max = 15, message = "Name can not greater then 15 Character")
    private String name;

    @NotEmpty
    @Email(message = "Email is not valid !!")
    private String email;

    @NotEmpty
    @Size(min = 4, max = 10, message = "Password must be min of 4 and max of 10 !!")
    private String password;

    @NotEmpty
    @Size(max = 500, message = "About can be max up to 500 Chaacter !!")
    private String about;

}
