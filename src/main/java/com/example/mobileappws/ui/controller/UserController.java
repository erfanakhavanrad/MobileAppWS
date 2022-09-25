package com.example.mobileappws.ui.controller;

import com.example.mobileappws.service.UserService;

import com.example.mobileappws.shared.dto.UserDto;
import com.example.mobileappws.ui.model.request.UserDetailsRequestModel;
import com.example.mobileappws.ui.model.response.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users") //http://localhost:8080/users
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public String getUser() {
        return "GET user was called.";
    }

    @PostMapping
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetailsRequestModel) {
        UserRest returnValue = new UserRest();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetailsRequestModel, userDto);

        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, returnValue);

        return returnValue;
//        System.out.println("Called");
//        return null;
    }

    @PutMapping
    public String updateUser() {
        return "UPDATE user was called.";
    }

    @DeleteMapping
    public String deleteUser() {
        return "DELETE user was called.";
    }


}
