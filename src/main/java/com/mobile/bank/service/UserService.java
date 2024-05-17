package com.mobile.bank.service;

import com.mobile.bank.model.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);

    User saveUser(User user);

    void deleteUserById(Long id);

    User updateUserById(Long id, User user);

    List<User> findAll();
}