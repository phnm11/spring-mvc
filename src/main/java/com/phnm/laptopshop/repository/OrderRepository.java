package com.phnm.laptopshop.repository;

import com.phnm.laptopshop.domain.Order;
import com.phnm.laptopshop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrderByUser(User user);
}
