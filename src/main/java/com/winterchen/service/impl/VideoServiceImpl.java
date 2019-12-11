package com.winterchen.service.impl;

import com.winterchen.model.VideoExt;
import com.winterchen.service.VideoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Override
    public List<VideoExt> getVideos() {
        return null;
    }

    @Override
    public List<VideoExt> getVideoByPerformer(VideoExt videoExt) {
        return null;
    }

    @Override
    public List<VideoExt> getVideoByLable(VideoExt videoExt) {
        return null;
    }
}
