package com.example.lfd1back.service;

import com.example.lfd1back.model.Cart;
import com.example.lfd1back.model.Dish;
import com.example.lfd1back.model.User;
import com.example.lfd1back.repository.CartRepository;
import com.example.lfd1back.service.interfaces.ICartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartService implements ICartService {

    @Autowired
    private final CartRepository cartRepository;

    @Override
    public boolean save(Cart cart) {
        try {
            cartRepository.save(cart);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Cart cart) {
        try {
            cartRepository.save(cart);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Cart cart) {
        try {
            cartRepository.delete(cart);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Cart getCart(Long userId){

        return cartRepository.getByUserId(userId);
    }

}
