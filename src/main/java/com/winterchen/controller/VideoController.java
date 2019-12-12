package com.winterchen.controller;

import com.winterchen.model.LableExt;
import com.winterchen.model.VideoExt;
import com.winterchen.service.LableService;
import com.winterchen.service.VideoService;
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

    @Autowired
    private VideoService videoService;

    @ResponseBody
    @GetMapping("/getLables")
    public List<LableExt> getLables(){
        List<VideoExt> videos = videoService.getVideos();
        VideoExt videoExt = new VideoExt();
        videoExt.setPerformerName("坂");
        videoExt.setLableName("丝");
        List<VideoExt> lable = videoService.getVideoByLable(videoExt);
        List<VideoExt> performer = videoService.getVideoByPerformer(videoExt);
        List<LableExt> lables = lableService.getLables();
        return lables;
    }



    @RequestMapping("/templates")
    public String index(){
//        return "welcome.html";
        return "/welcome";
    }
}
