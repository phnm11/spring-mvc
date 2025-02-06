package com.phnm.laptopshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.phnm.laptopshop.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User save(User newUser);

    List<User> findByEmail(String email);
}
