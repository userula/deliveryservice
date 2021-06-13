package com.example.lfd1back.repository;

import com.example.lfd1back.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart getByUserId(Long id);
}
