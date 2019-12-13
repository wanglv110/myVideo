package com.winterchen.model;

import java.util.Date;

public class PerformerExt extends Performer {

    private String performerId;
    private String performerName;
    private String performerNationality;
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

    public String getPerformerNationality() {
        return performerNationality;
    }

    public void setPerformerNationality(String performerNationality) {
        this.performerNationality = performerNationality;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
