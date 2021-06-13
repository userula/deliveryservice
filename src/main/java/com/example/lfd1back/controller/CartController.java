package com.example.lfd1back.controller;

import com.example.lfd1back.model.Cart;
import com.example.lfd1back.model.Dish;
import com.example.lfd1back.model.User;
import com.example.lfd1back.service.interfaces.ICartService;
import com.example.lfd1back.service.interfaces.IDishService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private final ICartService cartService;

    @Autowired
    private final IDishService dishService;

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

    @GetMapping("/getAllDishesFromCart")
    public String getAll(Model model, HttpSession session){

        User u = (User) session.getAttribute("user");

        Cart c = cartService.getCart(u.getId());

        model.addAttribute("cart", c);
        model.addAttribute("dishes", c.getDishes());

        return "cart";
    }

    @GetMapping("/addtocart")
    public void addtocart(@RequestParam(name = "id") Long id, HttpSession session){
        User u = (User) session.getAttribute("user");

        Cart c = cartService.getCart(u.getId());

        List<Dish> dishes = c.getDishes();

        Dish dish = dishService.getOne(id);

        dishes.add(dish);

        c.setDishes(dishes);

        cartService.save(c);
    }

}
