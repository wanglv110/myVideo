package com.winterchen.service.impl;

import com.winterchen.dao.VideoDao;
import com.winterchen.model.VideoExt;
import com.winterchen.service.VideoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional("transactionManager")
public class VideoServiceImpl implements VideoService {

    @Resource
    private VideoDao videoDao;

    @Override
    public List<VideoExt> getVideos(VideoExt videoExt) {
        List<VideoExt> select = videoDao.select(videoExt, videoDao.getMybatisNameSpace() + ".getVideos", videoExt.getPage());
        return select;
    }

    @Override
    public List<VideoExt> getVideoByPerformer(VideoExt videoExt) {
        List<VideoExt> select = videoDao.select(videoExt, videoDao.getMybatisNameSpace() + ".getVideoByPerformer", videoExt.getPage());
        return select;
    }

    @Override
    public List<VideoExt> getVideoByLable(VideoExt videoExt) {
        List<VideoExt> select = videoDao.select(videoExt, videoDao.getMybatisNameSpace() + ".getVideoByLable", videoExt.getPage());
        return select;
    }
}
