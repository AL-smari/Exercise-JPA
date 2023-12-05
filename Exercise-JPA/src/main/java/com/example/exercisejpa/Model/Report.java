package com.example.exercisejpa.Model;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "merchant id should not be null ")
    @Column(columnDefinition = "int not null")
    private Integer merchantID;
    @Size(max = 100,message = "message cant be more than 100")
    @Column(columnDefinition = "varchar(100)")
    private String message;

}
