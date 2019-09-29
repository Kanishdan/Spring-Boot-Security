package com.example.rolebasedsecurity.service;

import com.example.rolebasedsecurity.model.User;
import com.example.rolebasedsecurity.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DBInit implements CommandLineRunner {


    private UserRepository userRepository;

    public DBInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        this.userRepository.deleteAll();
        //Create Users
        User admin= new User("admin",passwordEncoder.encode("admin123"),"ADMIN","ACCESS_TEST1,ACCESS_TEST2");
        User agent = new User("agent",passwordEncoder.encode("agent123"),"AGENT","ACCESS_TEST1");
        User customer= new User("customer",passwordEncoder.encode("customer123"),"CUSTOMER","");
        List<User> users = Arrays.asList(admin,agent,customer);

        //save to db
        this.userRepository.saveAll(users);
    }


}
