package com.example.demo.service;

import com.example.demo.dto.AddressDto;

import java.util.List;

public interface AddressService {
    List<AddressDto> getAddresses(String userId);
    AddressDto getAddress(String addressId);


}
