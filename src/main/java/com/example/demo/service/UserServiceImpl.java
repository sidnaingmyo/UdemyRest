package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.UserServiceException;
import com.example.demo.repository.UserRepository;
import com.example.demo.response.ErrorMessages;
import com.example.demo.response.UserRest;
import org.apache.commons.lang3.RandomStringUtils;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    public UserDto getUser(String email) {
        UserEntity userEntity=userRepository.findByEmail(email);

        if(userEntity==null)throw new UsernameNotFoundException(email);

        UserDto returnValue=new UserDto();
        BeanUtils.copyProperties(userEntity,returnValue);
        return returnValue;
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        UserDto returnValue=new UserDto();
        UserEntity userEntity=userRepository.findByUserId(userId);
        if(userEntity==null)throw new UsernameNotFoundException("User with Id:"+userId+"  Not Found");
        BeanUtils.copyProperties(userEntity,returnValue);
        return returnValue;
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) {

        UserDto returnValue=new UserDto();
        UserEntity userEntity=userRepository.findByUserId(userId);

        if(userEntity==null)throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());

        UserEntity updatedUserDetails=userRepository.save(userEntity);

        BeanUtils.copyProperties(updatedUserDetails,returnValue);

        return returnValue;
    }
    @Transactional
    @Override
    public void deleteUser(String userId) {
        UserEntity userEntity=userRepository.findByUserId(userId);

        if(userEntity==null)throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        userRepository.delete(userEntity );

    }

    @Override
    public List<UserDto> getUsers(int page, int limit) {
        List<UserDto> returnValue=new ArrayList<>();

        if(page>0) page = page -1 ;


        Pageable pageableRequest= PageRequest.of(page, limit);
        Page<UserEntity> usersPage= userRepository.findAll(pageableRequest);
        List<UserEntity> users=usersPage.getContent();

        for(UserEntity userEntity:users){
            UserDto userDto=new UserDto();
            BeanUtils.copyProperties(userEntity,userDto);
            returnValue.add(userDto);
        }

        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity=userRepository.findByEmail(email);
        if(userEntity==null)throw new UsernameNotFoundException(email);

        return new User(userEntity.getEmail(),userEntity.getEncryptPassword(),new ArrayList<>());
    }
}
