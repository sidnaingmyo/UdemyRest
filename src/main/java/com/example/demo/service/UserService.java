package com.example.demo.service;

import com.example.demo.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDto create(UserDto user);
    UserDto getUser(String email);
    UserDto getUserByUserId(String userId);
    UserDto  updateUser(String userId ,UserDto user);
    void deleteUser(String userId);
    List<UserDto> getUsers(int page,int limit);

}
