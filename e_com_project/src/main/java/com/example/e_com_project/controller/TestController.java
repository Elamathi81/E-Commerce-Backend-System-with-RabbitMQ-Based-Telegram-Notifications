
package com.example.e_com_project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String home() {
        return "Welcome to the E-Commerce Project!";
    }

    @GetMapping("/ping")
    public String ping() {
        return "Application is up and running!";
    }
}
