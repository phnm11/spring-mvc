package com.phnm.laptopshop.service;

import com.phnm.laptopshop.domain.Role;
import com.phnm.laptopshop.domain.User;
import com.phnm.laptopshop.domain.dto.RegisterDTO;
import com.phnm.laptopshop.repository.RoleRepository;
import com.phnm.laptopshop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
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

    public Role getRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    public User registerDTOtoUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setFullName(registerDTO.getFirstName() + " " + registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        return user;
    }

    public boolean isEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
