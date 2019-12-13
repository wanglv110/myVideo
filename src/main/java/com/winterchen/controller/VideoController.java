package com.winterchen.controller;

import com.winterchen.commons.BaseResource;
import com.winterchen.model.LableExt;
import com.winterchen.model.PerformerExt;
import com.winterchen.model.VideoExt;
import com.winterchen.service.LableService;
import com.winterchen.service.PerformerService;
import com.winterchen.service.VideoService;
import com.winterchen.tools.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping(value = "/video")
public class VideoController extends BaseResource {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LableService lableService;

    @Autowired
    private PerformerService performerService;

    @Autowired
    private VideoService videoService;


    @ResponseBody
    @GetMapping("/getLables")
    public String getLables(){
        LableExt lableExt = new LableExt();
        List<LableExt> list = lableService.getLables(lableExt);
        String[] attr = {"lableId", "lableName", "updateTime"};
        if (page > 0) {
            totalCount = 10;
        }
        String returnStr = SystemUtils.formatJson("list", SystemUtils.formatList(list, attr), totalCount).toString();
        log.info("return={}", returnStr);
        return returnStr;
    }

    @ResponseBody
    @GetMapping("/getPerformers")
    public String getPerformers(){
        PerformerExt performerExt = new PerformerExt();
        List<PerformerExt> list = performerService.getPerformers(performerExt);
        String[] attr = {"performerId", "performerName", "performerNationality", "updateTime"};
        if (page > 0) {
            totalCount = 10;
        }
        String returnStr = SystemUtils.formatJson("list", SystemUtils.formatList(list, attr), totalCount).toString();
        log.info("return={}", returnStr);
        return returnStr;
    }

    @ResponseBody
    @GetMapping("/getVideos")
    public String getVideos(){
        VideoExt videoExt = new VideoExt();
        List<VideoExt> list = videoService.getVideos(videoExt);
        String[] attr = {"videoId", "name", "size", "videoType", "path", "designation", "performerName", "lableName"};
        if (page > 0) {
            totalCount = 10;
        }
        String returnStr = SystemUtils.formatJson("list", SystemUtils.formatList(list, attr), totalCount).toString();
        log.info("return={}", returnStr);
        return returnStr;
    }

    @ResponseBody
    @GetMapping("/getVideoByLable")
    public String getVideoByLable(@RequestParam("tabFlag") String LableName){
        VideoExt videoExt = new VideoExt();
        videoExt.setLableName(LableName);
        List<VideoExt> list = videoService.getVideoByLable(videoExt);
        String[] attr = {"videoId", "name", "size", "videoType", "path", "designation", "performerName", "lableName"};
        if (page > 0) {
            totalCount = 10;
        }
        String returnStr = SystemUtils.formatJson("list", SystemUtils.formatList(list, attr), totalCount).toString();
        log.info("return={}", returnStr);
        return returnStr;
    }

    @ResponseBody
    @GetMapping("/getVideoByPerformer")
    public String getVideoByPerformer(@RequestParam("tabFlag") String performerName){
        VideoExt videoExt = new VideoExt();
        videoExt.setPerformerName(performerName);
        List<VideoExt> list = videoService.getVideoByPerformer(videoExt);
        String[] attr = {"videoId", "name", "size", "videoType", "path", "designation", "performerName", "lableName"};
        if (page > 0) {
            totalCount = 10;
        }
        String returnStr = SystemUtils.formatJson("list", SystemUtils.formatList(list, attr), totalCount).toString();
        log.info("return={}", returnStr);
        return returnStr;
    }


    @RequestMapping("/templates")
    public String index(){
//        return "welcome.html";
        return "/welcome";
    }
}
