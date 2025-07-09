
package com.example.e_com_project.repository;

import com.example.e_com_project.entity.Order_items;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderItemRepo extends JpaRepository<Order_items, Long> {
}
