package com.example.exercisejpa.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "username should not be Empty")
    @Size(min = 5 , message = "username should not be less than 5")
    @Column(columnDefinition = "varchar(255) not null")
    private String username;
    @NotEmpty(message = "password should not be Empty")
    @Size(min = 6,message = "password should not be less than 6")
    @Pattern(regexp = "[0-9A-Za-z]+",message = "password should have characters and digits")
    @Column(columnDefinition = "varchar(255) not null")
    private String password;
    @NotEmpty(message = "Email should not be Empty")
    @Email(message = "must be valid Email")
    @Column(columnDefinition = "varchar(255) not null")
    private String email;
    @NotEmpty(message = "role should not be Empty")
    @Pattern(regexp = "Admin|Customer",message = "role must be Admin or Customer")
    @Column(columnDefinition = "varchar(255) not null check(role='admin' or role = 'customer')")
    private String role;
    @NotNull(message = "balance should not be Empty")
    @Positive(message = "balance should be positive")
    @Column(columnDefinition = "int not null check(balance>0)")
    private double balance;

}
