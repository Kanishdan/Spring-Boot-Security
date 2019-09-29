package com.example.rolebasedsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("agent")
public class AgentController {

    @GetMapping("index")
    public String agentPage()
    {
        return "agent/index";
    }
}
