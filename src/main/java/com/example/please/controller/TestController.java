package com.example.please.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

//    @GetMapping("/test")
//    public ModelAndView getSomething(){
//        ModelAndView model = new ModelAndView("test");
//        return model;
//    }

    @GetMapping("/test")
    public String getSomething(){
        return "test";
    }
}
