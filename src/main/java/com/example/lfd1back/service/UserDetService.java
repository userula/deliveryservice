package com.example.lfd1back.service;

import com.example.lfd1back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("myUserDetailsService")
public class UserDetService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

    private User getAuthenticatedUser(com.example.lfd1back.model.User user){
        return new User(user.getEmail(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                user.getAuthorities());
    }

}
