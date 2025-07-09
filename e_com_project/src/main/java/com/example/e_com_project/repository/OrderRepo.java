package com.example.e_com_project.repository;

import com.example.e_com_project.entity.Order;
import com.example.e_com_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {

    // Find all orders placed by a specific user
    List<Order> findByUser(User user);

    // Optional: Find orders by status
    List<Order> findByStatus(String status);

    // Optional: Find orders of a user by status
    List<Order> findByUserAndStatus(User user, String status);
}
