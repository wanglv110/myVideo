package com.winterchen.model;

import java.util.Date;

public class VideoExt extends Video {

    private String videoId;
    private String name;
    private Double size;
    private String videoType;
    private String logo;
    private String screenshot;
    private String path;
    private Integer definition;
    private String designation;
    private String describe;
    private Date uploadTime;
    private Date lssueTime;
    private Date updateTime;

    private String performerName;
    private String lableName;

    @Override
    public String getVideoId() {
        return videoId;
    }

    @Override
    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Double getSize() {
        return size;
    }

    @Override
    public void setSize(Double size) {
        this.size = size;
    }

    @Override
    public String getVideoType() {
        return videoType;
    }

    @Override
    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    @Override
    public String getLogo() {
        return logo;
    }

    @Override
    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String getScreenshot() {
        return screenshot;
    }

    @Override
    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public Integer getDefinition() {
        return definition;
    }

    @Override
    public void setDefinition(Integer definition) {
        this.definition = definition;
    }

    @Override
    public String getDesignation() {
        return designation;
    }

    @Override
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public String getDescribe() {
        return describe;
    }

    @Override
    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public Date getUploadTime() {
        return uploadTime;
    }

    @Override
    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Override
    public Date getLssueTime() {
        return lssueTime;
    }

    @Override
    public void setLssueTime(Date lssueTime) {
        this.lssueTime = lssueTime;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPerformerName() {
        return performerName;
    }

    public void setPerformerName(String performerName) {
        this.performerName = performerName;
    }

    public String getLableName() {
        return lableName;
    }

    public void setLableName(String lableName) {
        this.lableName = lableName;
    }
}
