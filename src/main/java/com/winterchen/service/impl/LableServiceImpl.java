package com.winterchen.service.impl;

import com.winterchen.dao.LableDao;
import com.winterchen.model.LableExt;
import com.winterchen.service.LableService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */
@Service
@Transactional("transactionManager")
public class LableServiceImpl implements LableService {

    @Resource
    private LableDao lableDao;

    @Override
    public List<LableExt> getLables(LableExt lableExt) {
        List<LableExt> select = lableDao.select(lableExt, lableDao.getMybatisNameSpace() + ".getLables" + lableExt.getPage());
        return select;
    }

    @Transactional
    @Override
    public int addLable(LableExt lableExt) {
        Integer insert = lableDao.insert(lableExt, lableDao.getMybatisNameSpace() + ".addLable");
        return insert;
    }
}
