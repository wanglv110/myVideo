package com.winterchen.dao.Impl;

import com.winterchen.commons.BaseDao;
import com.winterchen.dao.PerformerDao;
import com.winterchen.model.PerformerExt;
import org.springframework.stereotype.Repository;

/**
 * @Author: wjk
 * @Date: 2019/12/13
 */
@Repository
public class PerformerDaoImpl extends BaseDao<PerformerExt, String> implements PerformerDao {
    @Override
    public String getMybatisNameSpace() {
        return "myVideo.performer";
    }
}
