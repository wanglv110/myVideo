package com.winterchen.controller;

import com.winterchen.model.Lable;
import com.winterchen.service.user.LableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping(value = "/video")
public class VideoController {

    @Autowired
    private LableService lableService;

    @ResponseBody
    @GetMapping("/getLables")
    public List<Lable> getLables(){
        return lableService.getLables();
    }


    @RequestMapping("/templates")
    public String index(){
//        return "welcome.html";
        return "/welcome";
    }
}
