package com.example.rolebasedsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("customer")
public class CustomerController {
    @GetMapping("index")
    public String customerPage()
    {
        return "customer/index";
    }
}
