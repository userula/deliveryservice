package com.example.lfd1back.service.interfaces;

import com.example.lfd1back.model.User;

import java.util.List;

public interface IUserService {
    User findByEmail(String email);
    List<User> getAll();
    public User findById(Long id);
    boolean deleteById(Long id);
    boolean save(User user);
    boolean changePassword(String oldPassword, String newPassword, User u);
}
