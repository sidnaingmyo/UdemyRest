package com.example.demo.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class UserRest {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
}
