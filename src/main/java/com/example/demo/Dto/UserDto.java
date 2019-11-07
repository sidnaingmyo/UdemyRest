package com.example.demo.Dto;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class UserDto implements Serializable {

    private long id;

    private String userId;

    private  String firstName;

    private String lastName;

    private String email;

    private String password;

    private String encryptPassword;

    private String emailVerificationToken;

    private Boolean emailVerificationStatus=false;

}
