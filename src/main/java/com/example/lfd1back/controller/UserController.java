package com.example.lfd1back.controller;

import com.example.lfd1back.model.Cart;
import com.example.lfd1back.model.Dish;
import com.example.lfd1back.model.Restaurant;
import com.example.lfd1back.model.User;
import com.example.lfd1back.service.interfaces.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @Autowired
    private IRestaurantService restaurantService;

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

    @RequestMapping("/home")
    public String home(){
        return "userprof";
    }

    @PostMapping("/signup")
    public String signup(User user){
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
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

        List<Restaurant> reslist = restaurantService.getAll();
        model.addAttribute("restaurants", reslist);

        return "userprof";
    }

    @GetMapping("/userConf")
    public String userConf(){

        return "changepass";
    }

    @PostMapping("/changePass")
    public String userConfPost(HttpServletRequest request){
        String old = request.getParameter("oldpass");
        String newpass = request.getParameter("newpass");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if(userService.changePassword(old, newpass, user)){
            return "redirect:/userConf?success";
        }
        return "redirect:/userConf?error";
    }

}
