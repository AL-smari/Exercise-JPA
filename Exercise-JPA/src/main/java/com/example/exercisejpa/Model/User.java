package com.example.exercisejpa.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String username;
    @NotEmpty(message = "password should not be Empty")
    @Size(min = 6,message = "password should not be less than 6")
    @Pattern(regexp = "[0-9A-Za-z]+",message = "password should have characters and digits")
    private String password;
    @NotEmpty(message = "Email should not be Empty")
    @Email(message = "must be valid Email")
    private String email;
    @NotEmpty(message = "role should not be Empty")
    @Pattern(regexp = "Admin|Customer",message = "role must be Admin or Customer")
    private String role;
    @NotNull(message = "balance should not be Empty")
    @Positive(message = "balance should be positive")
    private double balance;

}
