package com.winterchen.service.impl;

import com.winterchen.dao.LableDao;
import com.winterchen.model.LableExt;
import com.winterchen.service.LableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */
@Service
public class LableServiceImpl implements LableService {

    @Autowired
    private LableDao lableDao;



    @Override
    public List<LableExt> getLables() {

        return lableDao.getLables();
    }

    @Override
    public void addLable(LableExt lableExt) {
        lableDao.addLable(lableExt);
    }
}
