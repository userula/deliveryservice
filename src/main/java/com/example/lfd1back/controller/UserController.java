package com.example.lfd1back.controller;

import com.example.lfd1back.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

//    @Autowired
//    private IUserService userService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
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
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
//        userService.save(user);

        return "redirect:/signup?success";
    }

    @PostMapping(path = "/login", consumes = "application/json")
    public ResponseEntity<?> login(@RequestBody User user) {
        User validatedUser = new User();
//        User validatedUser = userService.validateAndReturnUserOrThrowException(user.getUsername(), user.getPassword());
        return ResponseEntity.ok(validatedUser);
    }
}
