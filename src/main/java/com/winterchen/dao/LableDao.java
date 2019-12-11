package com.winterchen.dao;


import com.winterchen.model.Lable;
import com.winterchen.model.LableExt;

import java.util.List;

public interface LableDao {

    List<LableExt> getLables();

    void addLable(LableExt lableExt);

}