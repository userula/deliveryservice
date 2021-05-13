package com.example.lfd1back.controller;

import com.example.lfd1back.model.User;
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

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userService.save(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping(path = "/login", consumes = "application/json")
    public ResponseEntity<?> login(@RequestBody User user) {
        User validated = userService.findByEmail(user.getEmail());
        if(validated == null)
        {
            return ResponseEntity.ok("User doesn't exist!");
        }
        return ResponseEntity.ok(validated);
    }
}
