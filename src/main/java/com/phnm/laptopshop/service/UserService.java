package com.phnm.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.phnm.laptopshop.domain.User;
import com.phnm.laptopshop.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getAllUsersByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(User newUser) {
        User savedUser = userRepository.save(newUser);
        System.out.println(savedUser);
        return savedUser;
    }

    public User getUserById(long id) {
        return userRepository.findUserById(id);
    }

    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }
}
