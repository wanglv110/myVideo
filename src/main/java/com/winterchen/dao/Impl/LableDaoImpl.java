package com.winterchen.dao.Impl;

import com.winterchen.commons.BaseDao;
import com.winterchen.dao.LableDao;
import com.winterchen.model.LableExt;
import org.springframework.stereotype.Repository;

/**
 * @Author: wjk
 * @Date: 2019/12/13
 */
@Repository
public class LableDaoImpl extends BaseDao<LableExt, String> implements LableDao {
    @Override
    public String getMybatisNameSpace() {
        return "myVideo.lable";
    }
}
