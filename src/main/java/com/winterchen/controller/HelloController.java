package com.winterchen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping("/templates")
    public String index(){
//        return "welcome.html";
        return "/welcome";
    }
}
