package com.example.exercisejpa.Services;


import com.example.exercisejpa.Model.Merchant;
import com.example.exercisejpa.Model.MerchantStock;
import com.example.exercisejpa.Model.Product;
import com.example.exercisejpa.Repository.MerchantRepository;
import com.example.exercisejpa.Repository.MerchantStockRepository;
import com.example.exercisejpa.Repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantStockService {
    private final MerchantStockRepository merchantStockRepository;
    private final MerchantRepository merchantRepository;
    private final ProductRepository productRepository;




    public List<MerchantStock> getMerchantStock(){

        return merchantStockRepository.findAll();
    }
    public int addMerchantStock( MerchantStock merchantStock){
        int response =3;
        List<Product> products = productRepository.findAll();
        List<Merchant> merchants = merchantRepository.findAll();
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getId().equals(merchantStock.getProductID())){
                response =0;
                break;
            }else response =1;


        }if(response ==1){
            return response;
        }
        for (int i = 0; i < merchants.size(); i++) {
            if(merchants.get(i).getId()==merchantStock.getMerchantID()&&products.get(i).getId()==merchantStock.getProductID()){
                merchantStockRepository.save(merchantStock);
                response =2;
                break;

            }else response =3;

        }
        return response;


    }
    public int updateMerchantStock(Integer id,MerchantStock merchantStock){
        int response=0;
        List<Product> products = productRepository.findAll();
        List<Merchant> merchants = merchantRepository.findAll();
        MerchantStock merchantStock1 = merchantStockRepository.getById(id);
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getId()==merchantStock.getProductID()){
                response=0;
                break;
            }else response =1;


        }


        if(response!=0){
            return response;
        }else {
            for (int i = 0; i < merchants.size(); i++) {
                if(merchants.get(i).getId()==merchantStock.getMerchantID()){
                    response =0;
                    break;
                }else response=2;

            }

            if(response!=0){
                return response;
            }else {

                    if(merchantStock1==null){

                        response =4;

                    }else
                        merchantStock1.setStock(merchantStock.getStock());
                merchantStock1.setMerchantID(merchantStock.getMerchantID());
                merchantStock1.setProductID(merchantStock.getProductID());
                merchantStockRepository.save(merchantStock1);
                response =3;


            }
        }

        return response;

    }

    public boolean deleteMerchantStock(Integer id){
        MerchantStock merchantStock1 = merchantStockRepository.getById(id);

        if(merchantStock1==null){
            return false;
        }
        merchantStockRepository.delete(merchantStock1);
        return true;
    }


}
