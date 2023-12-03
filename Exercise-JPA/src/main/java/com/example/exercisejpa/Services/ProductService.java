package com.example.exercisejpa.Services;

import com.example.exercisejpa.Model.Category;
import com.example.exercisejpa.Model.Product;
import com.example.exercisejpa.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    ArrayList<Product>products=new ArrayList<>();


    public List<Product> getProducts(){

        return productRepository.findAll();
    }

    public boolean addProduct(List<Category> categories, Product product){
        for (int i = 0; i < categories.size(); i++) {
            if(categories.get(i).getId()==product.getCategoryID()){
                productRepository.save(product);
                return true;

            }

        }
        return false;

    }

    public int updateProduct(List<Category>categories , Integer id , Product product){
        for (int i = 0; i < categories.size(); i++) {
            if(categories.get(i).getId()!=product.getCategoryID()){
                return 1;

            }else break;

        }
        Product product1 = productRepository.getById(id);
        if(product1==null){
            return 3;
        }
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        product1.setCategoryID(product.getCategoryID());
        productRepository.save(product1);
        return 2;

    }
    public boolean deleteProduct(Integer id){

        Product product1 = productRepository.getById(id);
        if(product1==null){
            return false;
        }


        productRepository.delete(product1);
        return true;
    }



}