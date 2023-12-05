package com.example.exercisejpa.Model;


import jakarta.persistence.*;
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
    @Column(columnDefinition = "varchar(255) not null")
    private String name;
    @NotNull(message = "price must not be null")
    @Positive(message = "price must be positive number")
    @Column(columnDefinition = "int not null check(price>0)")
    private double price;
    @NotNull(message = "category ID must not be Emptys")
    @Column(columnDefinition = "int not null")
    private Integer categoryID;
}
