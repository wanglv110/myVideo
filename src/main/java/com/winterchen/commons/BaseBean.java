package com.winterchen.commons;

import com.github.pagehelper.Page;

import java.io.Serializable;

public class BaseBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String query_appId;
    private String query_customerKey;
    private String query_componentId;
    private Page<BaseBean> page = null;
    private String query_ip;
    private String query_orderBy;
    
    
    public String getQuery_ip() {
        return query_ip;
    }
    public void setQuery_ip(String query_ip) {
        this.query_ip = query_ip;
    }
    public String getQuery_appId() {
        return query_appId;
    }
    public void setQuery_appId(String query_appId) {
        this.query_appId = query_appId;
    }
    public String getQuery_customerKey() {
        return query_customerKey;
    }
    public void setQuery_customerKey(String query_customerKey) {
        this.query_customerKey = query_customerKey;
    }
    public Page<BaseBean> getPage() {
        return page;
    }
    public void setPage(int pageNum, int step) {
	this.page = new Page<BaseBean>();
        this.page.setPageNum(pageNum);
        this.page.setPageSize(step);
    }
    public String getQuery_componentId() {
        return query_componentId;
    }
    public void setQuery_componentId(String query_componentId) {
        this.query_componentId = query_componentId;
    }
    
    public void setOrderBy(String orderBy, String sort){
	query_orderBy = orderBy+" "+sort;
    }
    public String getQuery_orderBy() {
        return query_orderBy;
    }
    
}
