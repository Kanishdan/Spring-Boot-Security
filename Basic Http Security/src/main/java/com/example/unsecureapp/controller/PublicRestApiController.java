package com.example.unsecureapp.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/public")
public class PublicRestApiController {

    public PublicRestApiController(){}

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
}
