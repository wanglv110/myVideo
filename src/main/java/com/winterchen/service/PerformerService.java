package com.winterchen.service;

import com.winterchen.model.PerformerExt;
import com.winterchen.model.VideoExt;

import java.util.List;

/**
 * @Author: wjk
 * @Date: 2019/12/13
 */
public interface PerformerService {

    /**
     * 查询所有演员
     * @param performerExt
     * @return
     */
    List<PerformerExt> getPerformers(PerformerExt performerExt);

    /**
     * 添加演员
     * @param performerExt
     * @return
     */
    int addPerformer(PerformerExt performerExt);
}
