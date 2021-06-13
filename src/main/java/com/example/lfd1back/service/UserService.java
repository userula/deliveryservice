package com.example.lfd1back.service;

import com.example.lfd1back.model.User;
import com.example.lfd1back.repository.RoleRepository;
import com.example.lfd1back.repository.UserRepository;
import com.example.lfd1back.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean save(User user) {
        if(userRepository.existsByEmail(user.getEmail())){
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleRepository.getOne(2L));

        userRepository.save(user);

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.shutdown();
        return true;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
        if(userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public User findById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public boolean changePassword(String oldPass, String newPass, User u) {
        if (passwordEncoder.matches(oldPass, u.getPassword())){
            newPass = passwordEncoder.encode(newPass);
            userRepository.changePassword(newPass, u.getId());
            return true;
        }
        return false;
    }
}
