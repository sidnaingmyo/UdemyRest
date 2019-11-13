package com.example.demo.Ui;

import com.example.demo.Dto.UserDto;
import com.example.demo.Request.UserRequest;
import com.example.demo.Response.UserRest;
import com.example.demo.Service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public UserRest createUser(@RequestBody UserRequest request){

        UserRest returnvalue=new UserRest();

        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(request,userDto);

        UserDto create= userService.create(userDto);
        BeanUtils.copyProperties(create,returnvalue);

        return returnvalue;

    }
    @GetMapping(path="/{id}")
    public UserRest getuser(@PathVariable String id){


        UserRest returnvalue=new UserRest();
        UserDto userDto=userService.getUserByUserId(id);
        BeanUtils.copyProperties(userDto,returnvalue);

        return returnvalue;
    }
}
