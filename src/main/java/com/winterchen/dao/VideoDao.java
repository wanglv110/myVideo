package com.winterchen.dao;

import com.winterchen.model.VideoExt;

import java.util.List;

public interface VideoDao {

    /**
     * 查询所有视频
     * @return
     */
    List<VideoExt> getVideos();

    /**
     * 根据演员查询视频
     * @param videoExt
     * @return
     */
    List<VideoExt> getVideoByPerformer(VideoExt videoExt);

    /**
     * 根据标签查询视频
     * @param videoExt
     * @return
     */
    List<VideoExt> getVideoByLable(VideoExt videoExt);
}
