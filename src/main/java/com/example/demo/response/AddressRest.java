package com.example.demo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class AddressRest {

    private String addressId;
    private String city;
    private String country;
    private String streetName;
    private String postalCode;
    private String type;
}
