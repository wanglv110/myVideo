package com.winterchen.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winterchen.dao.LableDao;
import com.winterchen.dao.UserDao;
import com.winterchen.model.Lable;
import com.winterchen.model.UserDomain;
import com.winterchen.service.user.LableService;
import com.winterchen.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */
@Service(value = "userService")
public class LableServiceImpl implements LableService {

    @Autowired
    private LableDao lableDao;



    @Override
    public List<Lable> getLables() {

        return lableDao.getLables();
    }
}
