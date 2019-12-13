package com.winterchen.service.impl;

import com.winterchen.dao.PerformerDao;
import com.winterchen.model.PerformerExt;
import com.winterchen.model.VideoExt;
import com.winterchen.service.PerformerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: wjk
 * @Date: 2019/12/13
 */
@Service
@Transactional("transactionManager")
public class PerformerServiceImpl implements PerformerService {

    @Resource
    private PerformerDao performerDao;

    @Override
    public List<PerformerExt> getPerformers(PerformerExt performerExt) {
        List<PerformerExt> select = performerDao.select(performerExt, performerDao.getMybatisNameSpace() + ".getPerformers", performerExt.getPage());
        return select;
    }

    @Transactional
    @Override
    public int addPerformer(PerformerExt performerExt) {
        Integer insert = performerDao.insert(performerExt, performerDao.getMybatisNameSpace() + ".addPerformer");
        return insert;
    }
}
