package com.example.demo.ui;

import com.example.demo.dto.UserDto;
import com.example.demo.exception.UserServiceException;
import com.example.demo.request.UserRequest;
import com.example.demo.response.ErrorMessages;
import com.example.demo.response.UserRest;
import com.example.demo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(
            consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public UserRest createUser(@RequestBody UserRequest request)throws Exception{

        UserRest returnvalue=new UserRest();

        if(request.getFirstName().isEmpty()) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(request,userDto);

        UserDto create= userService.create(userDto);
        BeanUtils.copyProperties(create,returnvalue);

        return returnvalue;

    }
    @GetMapping(path="/{id}",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public UserRest getuser(@PathVariable String id){


        UserRest returnvalue=new UserRest();
        UserDto userDto=userService.getUserByUserId(id);
        BeanUtils.copyProperties(userDto,returnvalue);

        return returnvalue;
    }
}
