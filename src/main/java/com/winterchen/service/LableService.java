package com.winterchen.service;

import com.winterchen.model.LableExt;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19.
 */
public interface LableService {

    /**
     * 查询所有标签
     * @return
     */
    List<LableExt> getLables();

    /**
     * 添加标签
     * @param lableExt
     */
    void addLable(LableExt lableExt);

}
