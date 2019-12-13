package com.winterchen.commons;

/**
 * 
 * <p>文件名称: CustomerContextHolder.java</p>
 * <p>文件描述: 设置数据源的方法</p>
 * <p>完成日期：2015-12-11</p>
 * <p>修改记录0：无</p>
 * @version 1.0
 * @author  wjk
 */
public abstract class CustomerContextHolder {
    
    public final static String SESSION_FACTORY_MYSQL = "mysql";
    public final static String SESSION_FACTORY_ORACLE = "oracle";
    
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    
    public static void setContextType(String contextType) {
        contextHolder.set(contextType);  
    }  
      
    public static String getContextType() {
        return contextHolder.get();  
    }  
      
    public static void clearContextType() {  
        contextHolder.remove();  
    }  
}