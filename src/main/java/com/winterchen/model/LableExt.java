package com.winterchen.model;

import java.util.Date;

public class LableExt extends Lable {

    private String lableId;
    private String lableName;
    private Date updateTime;

    @Override
    public String getLableId() {
        return lableId;
    }

    @Override
    public void setLableId(String lableId) {
        this.lableId = lableId;
    }

    @Override
    public String getLableName() {
        return lableName;
    }

    @Override
    public void setLableName(String lableName) {
        this.lableName = lableName;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
