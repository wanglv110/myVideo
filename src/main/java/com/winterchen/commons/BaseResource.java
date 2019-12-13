package com.winterchen.commons;

import com.winterchen.tools.MD5;
import com.winterchen.tools.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * <p>文件名称: BaseResource.java</p>
 * <p>文件描述: 所有resource类的父类，做了一些公共参数的处理</p>
 * <p>完成日期：2015-12-16</p>
 * <p>修改记录0：无</p>
 * @version 1.0
 * @author  wjk
 */
public class BaseResource {
    
    Logger log = LoggerFactory.getLogger(this.getClass());

    //每个应用的APPId
    public String appid;
    
    //版本号
    public String v;
    
    //手机操作系统：ios、android、winphone、pc、wx、other
    public String os;
    
    //终端类型：1（手机客户端）、2（wap端）、3（PC端）、4（微信端）
    public String terminal;
    
    //安装渠道
    public String channel;
    
    //访客id，手机客户端生成规则参照第一视听的生成规则，wap、pc、微信端使用32位uuid
    public String userid;
    
    //设备号
    public String udid;
    
    //时间戳，用于安全性高的接口
    public String timestamp;
    
    //登录用户的令牌
    public String token;
    
    //签名，各个端的算法不同
    public String sign;
    
    //分页的page
    public int page = 0;
    
    //每页显示的数量，默认为10
    public int step;
    
    //登录用户的id
    public String customerKey = "-1";
    
    //向客户端返回的分页总数
    public int totalCount = -1;
    
    public String componentId;
    
    public String isExamine;
    
    public String ip;
    /**
     * 从request中获取公共参数，暂时未做校验
     * @param request
     */
    public void checkRequest(HttpServletRequest request){
//	appid = request.getParameter("appid");
//	log.info("componentId: "+componentId);
	//过滤器对wap端的APPId做过滤处理，所以需要从attribute中获取
	appid = (String) request.getAttribute("appId");
//	v = request.getParameter("v");
	v = (String) request.getAttribute("v");
	os = request.getParameter("os");
	terminal = request.getParameter("terminal");
	channel = request.getParameter("channel");
	userid = request.getParameter("userid");
	udid = request.getParameter("udid");
	timestamp = request.getParameter("timestamp");
	token = request.getParameter("token");
	sign = request.getParameter("sign");
	componentId = request.getParameter("componentId");
	isExamine = request.getParameter("isExamine");
	ip = SystemUtils.getIpAddr(request);
//	log.info("ip:"+ip);
	
	if(isExamine!=null && isExamine.equals("")){
	    isExamine = null;
	}
	if(componentId!=null&&componentId.equals("")){
	    componentId=null;
	}
	String pageStr = request.getParameter("page");
	if(pageStr != null && !pageStr.equals("")){
	    page = Integer.parseInt(pageStr);
	}
	String stepStr = request.getParameter("step");
	if(stepStr != null && !stepStr.equals("")){
	    step = Integer.parseInt(stepStr);
	} else {
	    step = Integer.parseInt(DictParameter.getProperty("step"));
	}
	customerKey = (String) request.getAttribute("customerId");
	if(customerKey == null){
	    customerKey = "-1";
	}

	log.info("appId={},v={},os={},udid={},token={},timestamp={},sign={},terminal={},customerKey={},ip={},componentId={},channel={}," +
			"isExamine={},page={},step={}", appid,v,os,udid,token,timestamp,sign,terminal,customerKey,ip,componentId,channel,isExamine,page,step);
    }
    
    /**
     * 从request中获取公共参数，并设置分页信息
     * @param request
     * @param bean
     */
    public void checkRequestPage(HttpServletRequest request, BaseBean bean){
	this.checkRequest(request);
	if(page > 0){
	    bean.setPage(page, step);
	}
    }
    
    /**
     * 签名验证，先调用checkRequest或者checkRequestPage方法给公共参数赋值
     * @return
     */
    public boolean checkSign(){
	if(v == null){
	    return false;
	}
	if(appid == null){
	    return false;
	}
	if(udid == null){
	    return false;
	}
	if(timestamp == null){
	    return false;
	}
	if(token == null){
//	    return false;
	    token = "";
	}
//	log.info(terminal);
	//客户端验证
	if(terminal.equals("1")){
	    if(os.equals("ios")){
		String s = "sign" + appid + v.replace(".", "") + timestamp + udid + token + appid + "iossign";
		log.info("s={}",s);
		log.info("md5={}", MD5.getMD5String(s));
		if(MD5.getMD5String(s).equals(sign) && this.CompareTime()){
		    return true;
		}
	    } else if(os.equals("android")){
		String s = "sign" + appid + timestamp + v.replace(".", "") + udid + token + appid + "androidsign";
		log.info("s={}",s);
		log.info("md5={}",MD5.getMD5String(s));
		if(MD5.getMD5String(s).equals(sign) && this.CompareTime()){
		    return true;
		}
	    } else if(os.equals("winphone")){
		String s = "sign" + appid + v.replace(".", "") + udid + timestamp + token + appid + "winphonesign";
		if(MD5.getMD5String(s).equals(sign) && this.CompareTime()){
		    return true;
		}
	    }
	} else {//其他端验证
	    String s = "sign" + appid + udid + token + timestamp + "sign";
	    log.info("s={}",s);
	    log.info("md5={}",MD5.getMD5String(s));
	    if(MD5.getMD5String(s).equals(sign) && this.CompareTime()){
		return true;
	    }
	}
	return false;
    }
    
    /**
     * 验证时间戳是否在有效期内
     * @return
     */
    public boolean CompareTime(){
	SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	try {
	    Date date = df.parse(timestamp);
	    long time = date.getTime();
//	long time = Long.parseLong(timestamp);
	    log.info("sub time={}", Math.abs(System.currentTimeMillis() - time));
	    if(Math.abs(System.currentTimeMillis() - time) < (Integer.parseInt(DictParameter.getProperty("timeLimit"))*60*1000)){
	        return true;
	    }
	} catch (NumberFormatException e) {
//	    e.printStackTrace();
	    log.error("数字格式化错误", e);
	} catch (ParseException e) {
//	    e.printStackTrace();
	    log.error("日期格式化错误", e);
	}
	return false;
    }
}
