package com.example.exercisejpa.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "must not be Empty")
    @Size(min = 3 ,message = "name must be more than 3 letters")
    private String name;
    @NotNull(message = "price must not be null")
    @Positive(message = "price must be positive number")
    private double price;
    @NotNull(message = "category ID must not be Emptys")
    private Integer categoryID;
}
