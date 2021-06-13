package com.example.lfd1back.controller;

import com.example.lfd1back.model.Cart;
import com.example.lfd1back.model.Dish;
import com.example.lfd1back.model.User;
import com.example.lfd1back.service.interfaces.ICartService;
import com.example.lfd1back.service.interfaces.IDeliveryService;
import com.example.lfd1back.service.interfaces.IDishService;
import com.example.lfd1back.service.interfaces.IUserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICartService cartService;

    @Autowired
    private IDeliveryService deliveryService;

    @Autowired
    private IDishService dishService;

    @RequestMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "index";
    }

    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(User user){
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);

        Cart c = new Cart();
        List<Dish> l = new LinkedList<>();
        c.setDishes(l);
        c.setUser(user);
        cartService.save(c);

        return "redirect:/signup?success";
    }

//    @PostMapping(path = "/login", consumes = "application/json")
//    public ResponseEntity<?> login(@RequestBody User user) {
//        User validated = userService.findByEmail(user.getEmail());
//        if(validated == null)
//        {
//            return ResponseEntity.ok("User doesn't exist!");
//        }
//        return ResponseEntity.ok(validated);
//    }

    @PostMapping("/delivery")
    public ResponseEntity<?> choosedelivery(){
        return ResponseEntity.ok(deliveryService.getFreeDel());
    }

    @RequestMapping("/userprof")
    public String userprof(Model model){

        List<Dish> list =  dishService.getAll();
        model.addAttribute("dishes", list);
        return "userprof";
    }


}
