package com.example.demo.Service;

import com.example.demo.Dto.UserDto;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto create(UserDto user) {

        UserEntity userEntity=new UserEntity();
        BeanUtils.copyProperties(user,userEntity);

        userEntity.setEncryptPassword("password");
        userEntity.setUserId("Id");

        UserEntity UserDetailStored=userRepository.save(userEntity);

        UserDto returnValue=new UserDto();
        BeanUtils.copyProperties(UserDetailStored,returnValue);

        return returnValue;
    }
}
