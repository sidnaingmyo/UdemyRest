package com.example.demo.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class UserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<AddressRequestModel> address;


}
