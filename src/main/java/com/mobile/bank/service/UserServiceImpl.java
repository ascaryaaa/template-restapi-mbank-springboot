package com.mobile.bank.service;

import com.mobile.bank.model.User;
import com.mobile.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUserById(Long id, User user) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();
            userToUpdate.setUser_name(user.getUser_name());
            userToUpdate.setUser_username(user.getUser_username());
            userToUpdate.setUser_password(user.getUser_password());
            userToUpdate.setUser_balance(user.getUser_balance());
            return userRepository.save(userToUpdate);
        } else {
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
