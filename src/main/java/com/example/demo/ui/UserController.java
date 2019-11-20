package com.example.demo.ui;

import com.example.demo.dto.AddressDto;
import com.example.demo.dto.UserDto;
import com.example.demo.request.UserRequest;
import com.example.demo.response.AddressRest;
import com.example.demo.response.OperationStatusModel;
import com.example.demo.response.RequestOperationStatus;
import com.example.demo.response.UserRest;
import com.example.demo.service.AddressService;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AddressService addressService;

    @PostMapping(
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserRest createUser(@RequestBody UserRequest request) throws Exception {

        UserRest returnvalue = new UserRest();

        if (request.getFirstName().isEmpty()) throw new NullPointerException("The Object is null");
//
//        UserDto userDto = new UserDto();
//        BeanUtils.copyProperties(request, userDto);

        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(request, UserDto.class);


        UserDto create = userService.create(userDto);
        returnvalue = modelMapper.map(create, UserRest.class);

//        UserDto create = userService.create(userDto);
//        BeanUtils.copyProperties(create, returnvalue);

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

    @DeleteMapping(path = "/{id}",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})

    public OperationStatusModel deleteUser(@PathVariable String id) {

        OperationStatusModel returnValue = new OperationStatusModel();

        returnValue.setOperationName(RequestOperationName.DELETE.name());

        userService.deleteUser(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return returnValue;
    }

    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public List<UserRest> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "limit", defaultValue = "2") int limit) {

        List<UserRest> returnValue = new ArrayList<>();
        List<UserDto> users = userService.getUsers(page, limit);

        for (UserDto userDto : users) {
            UserRest userModel = new UserRest();
            BeanUtils.copyProperties(userDto, userModel);
            returnValue.add(userModel);
        }

        return returnValue;
    }

    //http://localhost:8080/sisnaing/user/efaeaegagew/address
    @GetMapping(path = "/{id}/address", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public List<AddressRest> getuserAddresses(@PathVariable String id) {


        List<AddressRest> returnvalue = new ArrayList<>();
        List<AddressDto> addressDto = addressService.getAddresses(id);

        if (addressDto != null && !addressDto.isEmpty()) {
            Type listType = new TypeToken<List<AddressRest>>() {
            }.getType();
            ModelMapper modelMapper = new ModelMapper();
            returnvalue = modelMapper.map(addressDto, listType);

        }
        return returnvalue;
    }

    @GetMapping(path = "/{userid}/address/{addressId}",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})

    public AddressRest getuserAddress(@PathVariable String addressId,@PathVariable String userId) {

        AddressDto addressDto = addressService.getAddress(addressId);
        ModelMapper modelMapper = new ModelMapper();
//        Link addresslink=linkTo(methodOn(UserController.class).getUserAddress(userId,AddressId).withSelfRel();
//        Link userlink=linkTo(UserController.class).slash(userId).withRel("user");
//        Link addresseslink=linkTo(methodOn(UserController.class).getUserAddress(userId,AddressId).withRel("addresses ");
//        AddressRest addressRestModel=modelMapper.map(addressDto,AddressRest.class);
//        addressRestModel.add(addressLink);
//        addressRestModel.add(userLink);
//        addressRestModel.add(addressesLink);

        return modelMapper.map(addressDto, AddressRest.class);
    }

    @GetMapping(path = "/email-verificatoin",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public OperationStatusModel verifyEmailToken(@RequestParam(value = "token")String token){

        OperationStatusModel returnValue=  new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.VERIFY_EMAIL.name());

        boolean isVerified=userService.verifyEmailToken(token);

        if(isVerified){
            returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        }else {
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
        }
        return returnValue;

    }
}