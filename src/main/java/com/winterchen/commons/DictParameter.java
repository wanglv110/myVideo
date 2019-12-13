package com.winterchen.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

/**
 * 
 * <p>文件名称: DictParameter.java</p>
 * <p>文件描述: 启动时固定加载的数据</p>
 * <p>完成日期：2015-12-9</p>
 * <p>修改记录0：无</p>
 * @version 1.0
 * @author  wjk
 */
public class DictParameter implements Serializable {
    
    private static final Logger log = LoggerFactory.getLogger(DictParameter.class);
    private static HashMap<String, String> mSysCfg = null;

    private static DictParameter dictParameter = null;
    private static final long serialVersionUID = 1L;

    public synchronized static DictParameter getInstance() {
        if (dictParameter == null) {
            dictParameter = new DictParameter();
            dictParameter.loadSysCfg();
        }
        return dictParameter;
    }
    
    public void loadSysCfg()
    {
        log.info("载入系统参数配置");
        mSysCfg = new HashMap();
        try
        {
            String[] fileNames = {"system.properties"};
            for (String fileName : fileNames)
            {
                Properties p = new Properties();
                InputStream in = DictParameter.class.getResourceAsStream("/profile/" + fileName);
                p.load(in);
                in.close();
                in = null;
                Enumeration<?> keys = p.keys();
                while (keys.hasMoreElements())
                {
                    String key = (String) keys.nextElement();
                    String val = p.getProperty(key);
                    if (mSysCfg.containsKey(key))
                    {
                        log.error(fileName + "：" + key + "初始化出现异常，已经存在相同的key。");
                    }
                    mSysCfg.put(key, val.trim());
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            log.error("载入系统参数配置出错：" + e.getMessage());
        }
        log.info("载入系统参数配置 完成");
    }
    
    public static String getProperty(String key){
	String val = (String) mSysCfg.get(key);
	if(val == null){
	    val = "";
	}
	return val;
    }
}
