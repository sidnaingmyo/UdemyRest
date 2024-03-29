package com.example.demo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class UserRest {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private List<AddressRest> address;
}
