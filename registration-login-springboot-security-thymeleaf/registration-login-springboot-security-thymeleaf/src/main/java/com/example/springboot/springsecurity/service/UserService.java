package com.example.springboot.springsecurity.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.springboot.springsecurity.controller.dto.UserRegistrationDto;
import com.example.springboot.springsecurity.model.User;

//Services
//Our service layer will contain classes and interfaces that perform data access operations.
//UserService.java
//Here we define an interface that contains the method signatures for User service.   
//We need to be able to find the user by e-mail address or reset token.   Also, we need to be able to update the user's record.


public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}