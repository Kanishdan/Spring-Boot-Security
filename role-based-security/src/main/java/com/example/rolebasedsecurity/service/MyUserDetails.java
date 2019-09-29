package com.example.rolebasedsecurity.service;

import com.example.rolebasedsecurity.model.User;
import com.example.rolebasedsecurity.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetails implements UserDetailsService {

    private UserRepository userRepository;

    public MyUserDetails(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);
        if (username==null) {
            throw new UsernameNotFoundException("Please insert username");
        }

        MyUserDetailsImplementation myUserDetailsImplementation = new MyUserDetailsImplementation(user);

        return myUserDetailsImplementation;
    }
}
