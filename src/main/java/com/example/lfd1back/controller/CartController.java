package com.example.lfd1back.controller;

import com.example.lfd1back.model.Cart;
import com.example.lfd1back.service.interfaces.ICartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {

    @Autowired
    private final ICartService cartService;

    @GetMapping
    public ResponseEntity<?> index(){
        return ResponseEntity.ok("");
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Cart cart){
        cartService.save(cart);
        return ResponseEntity.ok("Cart was successfully added!");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestBody Cart cart){
        try {
            cartService.delete(cart);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Cart successfully deleted!");
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Cart cart){
        try {
            cartService.update(cart);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(cart);
    }

}
