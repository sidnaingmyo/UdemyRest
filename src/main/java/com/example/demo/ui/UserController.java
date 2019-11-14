package com.example.demo.ui;

import com.example.demo.dto.UserDto;
import com.example.demo.exception.UserServiceException;
import com.example.demo.request.UserRequest;
import com.example.demo.response.ErrorMessages;
import com.example.demo.response.OperationStatusModel;
import com.example.demo.response.RequestOperationStatus;
import com.example.demo.response.UserRest;
import com.example.demo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserRest createUser(@RequestBody UserRequest request) throws Exception {

        UserRest returnvalue = new UserRest();

        if (request.getFirstName().isEmpty()) throw new NullPointerException("The Object is null");

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(request, userDto);

        UserDto create = userService.create(userDto);
        BeanUtils.copyProperties(create, returnvalue);

        return returnvalue;

    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserRest getuser(@PathVariable String id) {


        UserRest returnvalue = new UserRest();
        UserDto userDto = userService.getUserByUserId(id);
        BeanUtils.copyProperties(userDto, returnvalue);

        return returnvalue;
    }

    @PutMapping(path = "/{id}",
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserRest updateUser(@RequestBody UserRequest request, @PathVariable String id) {

        UserRest returnValue = new UserRest();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(request, userDto);

        UserDto updateUser = userService.updateUser(id, userDto);
        BeanUtils.copyProperties(updateUser, returnValue);

        return returnValue;
    }

    @DeleteMapping(path= "/{id}",
    produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})

    public OperationStatusModel deleteUser(@PathVariable String id){

        OperationStatusModel returnValue =new OperationStatusModel();

        returnValue.setOperationName(RequestOperationName.DELETE.name());

        userService.deleteUser(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return returnValue;
    }

    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public List<UserRest> getUsers(@RequestParam(value = "page",defaultValue = "0")int page,
                                   @RequestParam(value = "limit",defaultValue = "2")int limit){

        List<UserRest> returnValue =new ArrayList<>();
        List<UserDto> users=userService.getUsers(page,limit);

        for(UserDto userDto:users){
        UserRest userModel=new UserRest();
        BeanUtils.copyProperties(userDto,userModel);
        returnValue.add(userModel);
        }

        return returnValue;
    }
}
