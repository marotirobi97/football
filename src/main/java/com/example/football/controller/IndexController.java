package com.example.football.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
@Controller
public class IndexController {
    @GetMapping("/index")
    public String initIndex() {
        return "index";
    }
}
