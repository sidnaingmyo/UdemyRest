package com.example.demo.service;

import com.example.demo.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto create(UserDto user);
    UserDto getUser(String email);
    UserDto getUserByUserId(String userId);

}
