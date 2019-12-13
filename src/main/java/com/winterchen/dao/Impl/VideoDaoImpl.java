package com.winterchen.dao.Impl;

import com.winterchen.commons.BaseDao;
import com.winterchen.dao.VideoDao;
import com.winterchen.model.VideoExt;
import org.springframework.stereotype.Repository;

/**
 * @Author: wjk
 * @Date: 2019/12/13
 */
@Repository
public class VideoDaoImpl extends BaseDao<VideoExt, String> implements VideoDao {
    @Override
    public String getMybatisNameSpace() {
        return "myVideo.video";
    }
}
