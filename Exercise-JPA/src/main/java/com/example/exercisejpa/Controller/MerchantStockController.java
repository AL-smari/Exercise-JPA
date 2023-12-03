package com.example.exercisejpa.Controller;

import com.example.exercisejpa.Model.MerchantStock;
import com.example.exercisejpa.Services.MerchantService;
import com.example.exercisejpa.Services.MerchantStockService;
import com.example.exercisejpa.Services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchantStock")
@RequiredArgsConstructor
public class MerchantStockController {

    private final MerchantStockService merchantStockService;
    private final ProductService productService;
    private final MerchantService merchantService;


    @GetMapping("/get")
    public ResponseEntity getMerchantStock(){
        return ResponseEntity.status(HttpStatus.OK).body(merchantStockService.getMerchantStock());
    }


    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@Valid @RequestBody MerchantStock merchantStock, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        int response= merchantStockService.addMerchantStock(productService.getProducts(),merchantService.getMerchants(),merchantStock);
        if(response==1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("product id not found");

        }else if(response==2){
            return ResponseEntity.status(HttpStatus.OK).body("merchantStock added");
        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("merchant id not found");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable Integer id , @Valid @RequestBody MerchantStock merchantStock , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        int response= merchantStockService.updateMerchantStock(productService.getProducts(),merchantService.getMerchants(),id,merchantStock);
        if(response==1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("product id not found");

        }else if(response==4){
            return ResponseEntity.status(HttpStatus.OK).body("merchantStock updated");
        }else if(response==2){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("merchant id not found");
        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable Integer id){
        if(merchantStockService.deleteMerchantStock(id)){
            return ResponseEntity.status(HttpStatus.OK).body("merchantStock deleted");
        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id not found");
    }
}