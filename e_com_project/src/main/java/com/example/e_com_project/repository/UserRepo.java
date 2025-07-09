
package com.example.e_com_project.repository;

import com.example.e_com_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
