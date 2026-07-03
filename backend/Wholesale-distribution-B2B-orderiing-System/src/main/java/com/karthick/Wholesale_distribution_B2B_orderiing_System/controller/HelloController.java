package com.karthick.Wholesale_distribution_B2B_orderiing_System.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "Welcome to Wholesale Distribution System";
    }
}
