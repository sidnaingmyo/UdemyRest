package com.example.demo.Service;

import com.example.demo.Dto.UserDto;
import com.example.demo.Entity.UserEntity;
import com.example.demo.Repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto create(UserDto user) {


        if(userRepository.findByEmail(user.getEmail()) != null) throw new RuntimeException("Email already exists");

        UserEntity userEntity=new UserEntity();
        BeanUtils.copyProperties(user,userEntity);

        userEntity.setEncryptPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userEntity.setUserId(RandomStringUtils.randomAlphanumeric(10));

        UserEntity UserDetailStored=userRepository.save(userEntity);

        UserDto returnValue=new UserDto();
        BeanUtils.copyProperties(UserDetailStored,returnValue);

        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
