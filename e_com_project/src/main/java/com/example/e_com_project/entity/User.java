package com.example.e_com_project.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "app_user") // Or "users"
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public User(Long id) {
        this.id = id;
    }

    private String username;
    private String email;
    private String password;

    public Long getId() {
        return id;
    }

    public User() {
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

