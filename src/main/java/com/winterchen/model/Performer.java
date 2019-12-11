package com.winterchen.model;

import java.util.Date;

public class Performer {
    private String performerId;
    private String performerName;
    private String pperformerNationality;
    private Date updateTime;

    public String getPerformerId() {
        return performerId;
    }

    public void setPerformerId(String performerId) {
        this.performerId = performerId;
    }

    public String getPerformerName() {
        return performerName;
    }

    public void setPerformerName(String performerName) {
        this.performerName = performerName;
    }

    public String getPperformerNationality() {
        return pperformerNationality;
    }

    public void setPperformerNationality(String pperformerNationality) {
        this.pperformerNationality = pperformerNationality;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
