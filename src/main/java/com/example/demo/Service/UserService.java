package com.example.demo.Service;

import com.example.demo.Dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto create(UserDto user);
}
