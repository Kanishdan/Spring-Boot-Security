package com.example.rolebasedsecurity.controller;

import com.example.rolebasedsecurity.model.User;
import com.example.rolebasedsecurity.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/public")
public class RestApiController {

    private UserRepository userRepository;
    public RestApiController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("test1")
    public  String test1()
    {
        return "Test Api1";
    }
    @GetMapping("test2")
    public  String test2()
    {
        return "Test Api2";
    }

    @GetMapping("users")
    public List<User> users()
    {
        return  this.userRepository.findAll();
    }
}