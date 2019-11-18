package com.example.demo.entity;

import com.example.demo.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "address")
@Getter @AllArgsConstructor
@Setter @NoArgsConstructor
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;
    @Column(length = 30,nullable = false)
    private String addressId;

    @Column(length = 15,nullable = false)
    private String city;

    @Column(length = 15,nullable = false)
    private String country;

    @Column(length = 100,nullable = false)
    private String streetName;

    @Column(length = 7,nullable = false)
    private String postalCode;

    @Column(length = 10,nullable = false)
    private String type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userDetails;
}
