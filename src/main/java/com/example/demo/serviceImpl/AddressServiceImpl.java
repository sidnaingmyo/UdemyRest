package com.example.demo.serviceImpl;

import com.example.demo.dto.AddressDto;
import com.example.demo.entity.AddressEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.AddressRepository;
import com.example.demo.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;

    @Override
    public List<AddressDto> getAddresses(String userId) {
        List<AddressDto> returnValue=new ArrayList<>();
        ModelMapper modelMapper=new ModelMapper();
        UserEntity userEntity= userRepository.findByUserId(userId);
        if(userEntity==null) return  returnValue;
        Iterable<AddressEntity> address= addressRepository.findAllByUserDetails(userEntity);
        for(AddressEntity addressEntity:address){

            returnValue.add(modelMapper.map(addressEntity,AddressDto.class));
        }
        return returnValue;
    }

    @Override
    public AddressDto getAddress(String addressId) {

        AddressDto returnValue=null;

        AddressEntity addressEntity=addressRepository.findByAddressId(addressId);
        if(addressEntity!=null){
            returnValue=new ModelMapper().map(addressEntity,AddressDto.class);
        }


        return returnValue;
    }
}
