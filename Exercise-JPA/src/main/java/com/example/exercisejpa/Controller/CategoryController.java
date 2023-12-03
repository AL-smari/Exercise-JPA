package com.example.exercisejpa.Controller;

import com.example.exercisejpa.Model.Category;
import com.example.exercisejpa.Services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity getCategory(){

        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getCategories());
    }
    @PostMapping("/add")
    public ResponseEntity addCategory(@Valid @RequestBody Category category, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        categoryService.addCategory(category);
        return ResponseEntity.status(HttpStatus.OK).body("Category added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory(@PathVariable Integer id , @Valid@RequestBody Category category,Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }else if(categoryService.updateCategory(id,category)){
            return ResponseEntity.status(HttpStatus.OK).body("category updated");
        }else  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id not found");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id){
        if(categoryService.deleteCategory(id)){
            return ResponseEntity.status(HttpStatus.OK).body("category deleted");
        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id not found");
    }
}
