package com.example.exercisejpa.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MerchantStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "product id should not be Empty")
    @Column(columnDefinition = "int not null")
    private Integer productID;
    @NotNull(message = "merchant id should not be Empty")
    @Column(columnDefinition = "int not null")
    private Integer merchantID;
    @NotNull(message = "stock should not be null")
    @Min(value = 10,message = "stock should be at least 10")
    @Column(columnDefinition = "int not null check(stock>=10)")
    private Integer stock;
}
