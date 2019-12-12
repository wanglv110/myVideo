package com.winterchen.service.impl;

import com.winterchen.dao.VideoDao;
import com.winterchen.model.VideoExt;
import com.winterchen.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoDao videoDao;

    @Override
    public List<VideoExt> getVideos() {
        return videoDao.getVideos();
    }

    @Override
    public List<VideoExt> getVideoByPerformer(VideoExt videoExt) {
        return videoDao.getVideoByPerformer(videoExt);
    }

    @Override
    public List<VideoExt> getVideoByLable(VideoExt videoExt) {
        return videoDao.getVideoByLable(videoExt);
    }
}
