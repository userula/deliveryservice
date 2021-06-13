package com.example.lfd1back.service.interfaces;

import com.example.lfd1back.model.Cart;

public interface ICartService {
    boolean save(Cart cart);
    boolean update(Cart cart);
    boolean delete(Cart cart);
    Cart getCart(Long id);
}
