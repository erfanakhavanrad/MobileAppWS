package com.example.mobileappws.service;


import com.example.mobileappws.shared.dto.UserDto;
import org.springframework.stereotype.Repository;


public interface UserService {
    UserDto createUser(UserDto user);
}
