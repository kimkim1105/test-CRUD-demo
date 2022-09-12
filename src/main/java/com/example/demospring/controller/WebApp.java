package com.example.demospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
@Controller
@RequestMapping( "/index")
public class WebApp  extends SpringBeanAutowiringSupport {
   @GetMapping
    public String index() {
        return "index";
    }
}
