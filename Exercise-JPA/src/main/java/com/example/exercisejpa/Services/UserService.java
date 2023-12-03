package com.example.exercisejpa.Services;

import com.example.exercisejpa.Model.MerchantStock;
import com.example.exercisejpa.Model.Product;
import com.example.exercisejpa.Model.Report;
import com.example.exercisejpa.Model.User;
import com.example.exercisejpa.Repository.MerchantStockRepository;
import com.example.exercisejpa.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final MerchantStockRepository merchantStockRepository;




    public List<User> getUsers() {

        return userRepository.findAll();
    }

    public void addUser(User user) {

        userRepository.save(user);
    }

    public boolean updateUser(Integer id, User user) {
        User user1 = userRepository.getById(id);

        if(user1==null) {
            return false;
        }
        user1.setUsername(user.getUsername());
        user1.setBalance(user.getBalance());
        user1.setRole(user.getRole());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        userRepository.save(user1);
        return true;
    }

    public boolean deleteUser(Integer id) {
        User user1 = userRepository.getById(id);

        if(user1==null) {
            return false;
        }
        userRepository.delete(user1);
        return true;

    }


    public boolean addMoreStocks(List<MerchantStock> merchantStocks, Integer productID, Integer merchantID, int amount) {

        for (int i = 0; i < merchantStocks.size(); i++) {

            if (merchantStocks.get(i).getMerchantID()==merchantID && merchantStocks.get(i).getProductID()==productID) {
                merchantStocks.get(i).setStock(merchantStocks.get(i).getStock() + amount);
                merchantStockRepository.save(merchantStocks.get(i));

                return true;
            }

        }
        return false;


    }

    public int userBuy(List<MerchantStock> merchantStocks, List<Product> products, Integer userID, Integer productID, Integer merchantID) {
        int response = 0;

        User user = userRepository.getById(userID);
        MerchantStock merchantStock = merchantStockRepository.getById(merchantID);


            if (user!=null) {
                response = 0;


            } else response = 1;


        if (response != 0) {
            return response;
        }


            if (merchantStock!=null) {

                response = 0;


            } else response = 2;


        if (response != 0) {
            return response;
        }

        if (merchantStock!=null) {

            if (merchantStock.getProductID()==productID && merchantStock.getMerchantID()==merchantID) {

                response = 0;

            } else response = 3;

        }
        if (response != 0) {
            return response;
        }

        if (merchantStock.getStock() >= 1) {
            response = 0;
        } else response = 4;
        if (response != 0) {
            return response;
        }

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId()==productID)
                if (user.getBalance() >= products.get(i).getPrice()) {
                    user.setBalance(user.getBalance() - products.get(i).getPrice());
                    userRepository.save(user);
                  
                    response = 0;
                    break;
                } else response = 5;

        }

        return response;

    }

    public int banMerchant(Integer id, Integer merchantID, List<Report> reports) {
        int response = 0;
        int userTemp = 0;
        User user = userRepository.getById(id);

            if (user!=null) {
                response = 0;


            } else response = 1;


        if (response != 0) {
            return response;
        }
        if (user.getRole().equals("Admin")) {
            response = 0;
        } else response = 2;


        if (response != 0) {
            return response;
        }

        for (int i = 0; i < reports.size(); i++) {
            if (reports.get(i).getMerchantID()==merchantID) {
                response = 0;
                return response;
            }else response =3;
        }



        return response;
    }

    public boolean getReports(Integer id){
        User user = userRepository.getById(id);
        if(user!=null) {
            if(user.getId().equals(id)&&user.getRole().equals("Admin")){
                return true;
            }

        }
        return false;
    }
}
