package com.winterchen.tools;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * <p>
 * 文件名称: SystemUtils.java
 * </p>
 * <p>
 * 文件描述: 系统工具类，包括格式化返回数据等
 * </p>
 * <p>
 * 完成日期：2015-12-15
 * </p>
 * <p>
 * 修改记录0：无
 * </p>
 * 
 * @version 1.0
 * @author wjk
 */
public class SystemUtils {

    static Logger log = LoggerFactory.getLogger(SystemUtils.class);

    /**
     * 生成返回的json串,单条记录
     * 
     * @param ret
     *            操作码
     * @param msg
     *            提示消息
     * @param obj
     *            body里的值
     * @return
     */
    public static JSONObject formatJsonBody(int ret, String msg, Object obj) {
//	JSONObject json = new JSONObject();
	Map<String, Object> map = new HashMap<String, Object>();
//	json.accumulate("code", 200);
	map.put("code", 200);
//	JSONObject json1 = new JSONObject();
	Map<String, Object> map1 = new HashMap<String, Object>();
//	json1.accumulate("ret", ret);
	map1.put("ret", ret);
//	json1.accumulate("msg", msg);
	map1.put("msg", msg);
//	json1.accumulate("body", obj);
	map1.put("body", obj);
//	json.accumulate("results", json1);
	map.put("results", map1);
	return JSONObject.fromObject(map);
    }

    /**
     * 生成返回的json串,多条记录
     * 
     * @param ret
     *            操作码
     * @param msg
     *            提示消息
     * @param obj
     *            list里的值
     * @return
     */
    public static JSONObject formatJsonList(int ret, String msg, Object obj,
                                            int count) {
//	JSONObject json = new JSONObject();
	Map<String, Object> map = new HashMap<String, Object>();
//	json.accumulate("code", 200);
	map.put("code", 200);
//	JSONObject json1 = new JSONObject();
	Map<String, Object> map1 = new HashMap<String, Object>();
//	json1.accumulate("ret", ret);
	map1.put("ret", ret);
//	json1.accumulate("msg", msg);
	map1.put("msg", msg);
	if (count >= 0) {
//	    json1.accumulate("totalCount", count);
	    map1.put("totalCount", count);
	}
//	json1.accumulate("list", obj);
	map1.put("list", obj);
//	json.accumulate("results", json1);
	map.put("results", map1);
	return JSONObject.fromObject(map);
    }

    /**
     * 生成返回的json串,无记录
     * 
     * @param ret
     *            操作码
     * @param msg
     *            提示消息
     * @return
     */
    public static JSONObject formatJsonNone(int ret, String msg) {
//	JSONObject json = new JSONObject();
	Map<String, Object> map = new HashMap<String, Object>();
//	json.accumulate("code", 200);
	map.put("code", 200);
//	JSONObject json1 = new JSONObject();
	Map<String, Object> map1 = new HashMap<String, Object>();
//	json1.accumulate("ret", ret);
	map1.put("ret", ret);
//	json1.accumulate("msg", msg);
	map1.put("msg", msg);
//	json.accumulate("results", json1);
	map.put("results", map1);
	return JSONObject.fromObject(map);
    }

    /**
     * 默认成功的返回json串的方法,不返回totalCount
     * 
     * @param type
     *            值为body，list或者none
     * @param obj
     * @return
     */
    public static JSONObject formatJson(String type, Object obj) {
	return SystemUtils.formatJson(type, obj, -1);
    }

    /**
     * 默认成功的返回json串的方法,返回totalCount
     * 
     * @param type
     *            值为body，list或者none
     * @param obj
     * @param count
     *            返回的totalCount，不需要返回时传-1
     * @return
     */
    public static JSONObject formatJson(String type, Object obj, int count) {
	JSONObject json = new JSONObject();
	if (type.equals("body")) {
//	    json = SystemUtils.formatJsonBody(100, "成功", obj);
	    return SystemUtils.formatJsonBody(100, "成功", obj);
	} else if (type.equals("list")) {
//	    json = SystemUtils.formatJsonList(100, "成功", obj, count);
	    return SystemUtils.formatJsonList(100, "成功", obj, count);
	} else if (type.equals("none")) {
//	    json = SystemUtils.formatJsonNone(100, "成功");
	    return SystemUtils.formatJsonNone(100, "成功");
	}
	return json;
    }

    /**
     * 格式化json串中body节点的数据
     *
     * @param object
     *            需要格式化的bean类
     * @param attr
     *            需要格式化的字段
     * @return
     */
    public static JSONObject formatBody(Object object, String[] attr) {
	Map<String, Object> map = new HashMap();
	if (object == null) {
	    return JSONObject.fromObject(map);
	}

	try {
	    for (int i = 0; i < attr.length; i++) {
		Field field = object.getClass().getDeclaredField(attr[i]);
		field.setAccessible(true);
		Object value = field.get(object);
		if (null == value) {
		    value = "";
		}

		map.put(attr[i], value);
	    }
	} catch (NoSuchFieldException e) {
//	    e.printStackTrace();
	    log.error("NoSuchFieldException", e);
	} catch (IllegalAccessException e) {
//	    e.printStackTrace();
	    log.error("IllegalAccessException", e);
	}
	return JSONObject.fromObject(map);
    }

    /**
     * 格式化json串中list节点的数据
     *
     * @param list
     *            需要格式化的bean列表
     * @param attr
     *            需要格式化的字段
     * @return
     */
    public static <E> List<String> formatList(List<E> list, String[] attr) {
	List<String> jsonList = new ArrayList<String>();
	if (list != null) {
	    for (Object object : list) {
		jsonList.add(SystemUtils.formatBody(object, attr).toString());
	    }
	}
	return jsonList;
    }

    /**
     * 根据传入的日期获取是星期几
     * 
     * @param dayTime
     *            格式yyyy-MM-dd
     * @return 1-7
     * @throws ParseException
     */
    public static String getWeek(String dayTime) throws ParseException {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date date = sdf.parse(dayTime);
	Calendar cal = Calendar.getInstance();
	cal.setTime(date);
	int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
	if (w == 0) {
	    w = 7;
	}
	return w + "";
    }

    /**
     * 比较两个时间的先后
     * 
     * @param time1
     *            格式yyyy-MM-dd HH:mm
     * @param time2
     *            Date类型
     * @return true表示time1在time2之前，false表示time1在time2之后
     */
    public static boolean compareTime(String time1, Date time2) {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	boolean b = false;
	try {
	    Date d1 = sdf.parse(time1);
	    b = d1.before(time2);
	} catch (ParseException e) {
//	    e.printStackTrace();
	    log.error("时间格式化错误", e);
	}
	return b;
    }

    /**
     * 创建新短信码
     * 
     * @return
     */
    public static String createSmsCode() {
	StringBuffer password = new StringBuffer();
	Random random = new Random();
	for (int i = 0; i < 6; i++) {
	    password.append((char) ('0' + random.nextInt(10)));
	}

	return password.toString();
    }

    /**
     * 验证短信码
     * 
     * @return
     */
    public static boolean checkSmsCode() {
	return true;
    }



    /**
     * 格式化评论时间
     * 
     * @param time
     * @return
     * @return
     */
    public static String getDateStr(String time) {
	Calendar calendar = Calendar.getInstance();
	Date now = new Date();
	calendar.setTime(now);
	int now_year = calendar.get(Calendar.YEAR);
	int now_month = calendar.get(Calendar.MONTH);
	int now_date = calendar.get(Calendar.DATE);
	int now_seconds = (int) (now.getTime() / 1000);

	try {
	    SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date d = a.parse(time);
	    calendar.setTime(d);

	    int d_year = calendar.get(Calendar.YEAR);
	    int d_month = calendar.get(Calendar.MONTH);
	    int d_date = calendar.get(Calendar.DATE);
	    int d_seconds = (int) (d.getTime() / 1000);

	    if (d_year != now_year) {
		// 往年
		return time.substring(0, 10);
	    } else if (d_month == now_month && d_date == now_date) {
		// 今天
		int seconds = now_seconds - d_seconds;
		seconds = seconds <= 0 ? 1 : seconds;
		if (seconds < 120) {
		    return "1分钟前";
		} else if (seconds < 3600) {
		    return ((int) seconds / 60) + "分钟前";
		} else {
		    return ((int) seconds / 3600) + "小时前";
//		    return "今天 " + time.substring(11, 16);
		}
	    }
	} catch (ParseException e) {
//	    e.printStackTrace();
	    log.error("时间格式化错误", e);
	}
	// 今年，非今天
	return time.substring(5, 16);
    }

    /**
     * 计算两个日期相隔的天数
     * 
     * @param firstString
     *            较早的时间
     * @param secondString
     *            较晚的时间
     * @return
     */
    public static int nDaysBetweenTwoDate(String firstString,
                                          String secondString) {
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	Date firstDate = null;
	Date secondDate = null;
	try {
	    firstDate = df.parse(firstString);
	    secondDate = df.parse(secondString);
	} catch (Exception e) {
	    // 日期型字符串格式错误
	    log.error("时间格式化错误", e);
	}

	int nDay = (int) ((secondDate.getTime() - firstDate.getTime()) / (24 * 60 * 60 * 1000));
	return nDay;
    }

    /**
     * 计算百分比
     * 
     * @param a
     *            分子
     * @param total
     *            总数
     * @return
     */
    public static String percentage(int a, int total) {
	if(total == 0){
	    return "0%";
	}
	double f = (double) a * 100 / (double) total;
	DecimalFormat format = new DecimalFormat("#0");
	String buf = format.format(f).toString();
	return buf + "%";
    }

    /**
     * 创建用户名称
     * 
     * @return
     */
    public static String createCustomerName() {
	StringBuffer password = new StringBuffer();
	Random random = new Random();
	for (int i = 0; i < 11; i++) {
	    password.append((char) ('0' + random.nextInt(10)));
	}

	return "用户" + password.toString();
    }

    /**
     * 从request中获取ip地址
     * 
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {

	String ipAddress = null;
	ipAddress = request.getHeader("x-forwarded-for");
	log.info("x-forwarded-for={}", ipAddress);
	if (ipAddress == null || ipAddress.length() == 0
		|| "unknown".equalsIgnoreCase(ipAddress)) {
	    ipAddress = request.getHeader("X-Forwarded-For");
	    log.info("X-Forwarded-For={}", ipAddress);
	}
	if (ipAddress == null || ipAddress.length() == 0
			|| "unknown".equalsIgnoreCase(ipAddress)) {
		    ipAddress = request.getRemoteAddr();
		    log.info("RemoteAddr={}", ipAddress);
		    if (ipAddress.equals("127.0.0.1")) {
			// 根据网卡取本机配置的IP
			InetAddress inet = null;
			try {
			    inet = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
//			    e.printStackTrace();
			    log.error("UnknownHostException", e);
			}
			ipAddress = inet.getHostAddress();
			log.info("根据网卡取本机配置的IP={}", ipAddress);
		    }

		}
	if (ipAddress == null || ipAddress.length() == 0
		|| "unknown".equalsIgnoreCase(ipAddress)) {
	    ipAddress = request.getHeader("X-Real-IP");
	    log.info("X-Real-IP={}", ipAddress);
	}
	if (ipAddress == null || ipAddress.length() == 0
		|| "unknown".equalsIgnoreCase(ipAddress)) {
	    ipAddress = request.getHeader("Proxy-Client-IP");
	    log.info("Proxy-Client-IP={}", ipAddress);
	}
	if (ipAddress == null || ipAddress.length() == 0
		|| "unknown".equalsIgnoreCase(ipAddress)) {
	    ipAddress = request.getHeader("WL-Proxy-Client-IP");
	    log.info("WL-Proxy-Client-IP={}", ipAddress);
	}
	if (ipAddress == null || ipAddress.length() == 0
		|| "unknown".equalsIgnoreCase(ipAddress)) {
	    ipAddress = request.getRemoteAddr();
	    if (ipAddress.equals("127.0.0.1")) {
		// 根据网卡取本机配置的IP
		InetAddress inet = null;
		try {
		    inet = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
//		    e.printStackTrace();
		    log.error("UnknownHostException", e);
		}
		ipAddress = inet.getHostAddress();
	    }

	}
	// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
	if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
							    // = 15
	    if (ipAddress.indexOf(",") > 0) {
		ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
	    }
	}
	return ipAddress;
    }

    /**
     * 判断是否是微信登录
     * 
     * @param request
     * @return
     */
    public static boolean iswxorqq(HttpServletRequest request) {

	if (request.getHeader("user-agent") == null) {
	    return false;
	}
	String header = (request.getHeader("user-agent")).toLowerCase();

	if (header.indexOf("micromessenger/") == -1
		&& header.indexOf("mqqbrowser/") == -1
		&& header.indexOf(" qq/") == -1) {

	    return false;

	} else {

	    return true;

	}
    }
    
    public static String getTimeForSql(){
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	return sdf.format(new Date());
    }
    
    public static String getDateForSql(){
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	return sdf.format(new Date());
    }
    
    /**
     * 格式化用户名称，如果是手机号的，中间4位转换成****
     * @param customerName
     * @return
     */
    public static String fomatCustomerName(String customerName){
	if(customerName!=null && customerName.length()==11){
	    try {
		Long.parseLong(customerName);
		if(customerName.startsWith("1")){
		    customerName = customerName.substring(0, 3)+"****"+customerName.substring(7);
		}
	    } catch (NumberFormatException e) {
		return customerName;
	    }
	}
	return customerName;
    }
    
    /**
     * 获取一个小时的秒数，如果接近24时，则获取当前时间到24时的秒数
     * @return
     */
    @SuppressWarnings("deprecation")
    public static int getSeconds(){
	int time = 3600;
	if(new Date().getHours() == 23){
	    Date date = new Date();
	    int s = date.getSeconds();
	    int m = date.getMinutes();
	    time = time - (m*60+s);
	}
	return time;
    }
    
    /**
     * 格式化访问量，超过1万的数字用万来表示，保留一位小数，第二位小数四舍五入
     * @param countStr String类型的访问量 
     * @return
     */
    public static String formatCount(String countStr){
	String result = "";
	if(countStr == null || countStr.equals("")){
	    result = "0";
	} else {
	    long count = Long.parseLong(countStr);
	    if(count >= 10000){
		double f = (double)count/10000;
		BigDecimal b = new BigDecimal(f);
		double f1 = b.setScale(1, RoundingMode.HALF_UP).doubleValue();
		result = String.valueOf(f1)+"万";
	    } else {
		result = countStr;
	    }
	}
	return result;
    }
    
    
    /**
     * java生成随机数字和字母组合
     * 
     * @param length
     *            [生成随机数的长度]
     * @return
     */
    public static String getCharAndNumr(int length) {
	String val = "";
	Random random = new Random();
	for (int i = 0; i < length; i++) {
	    // 输出字母还是数字
	    String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
	    // 字符串
	    if ("char".equalsIgnoreCase(charOrNum)) {
		// 取得大写字母还是小写字母
		int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
		val += (char) (choice + random.nextInt(26));
	    } else if ("num".equalsIgnoreCase(charOrNum)) { // 数字
		val += String.valueOf(random.nextInt(10));
	    }
	}
	return val.toUpperCase();
    }

    /**
     * 得到XXXX年XX月XX日格式的日期
     * @param dateStr XXXX-XX-XX
     * @return
     */
    public static String getChineseDate(String dateStr){
	String date[] = dateStr.split("-");
	if(date.length >= 3){
	    return date[0]+"年"+date[1]+"月"+date[2]+"日";
	} else {
	    return dateStr;
	}
    }
    /**
	 * 字符型日期转化util.Date型日期
	 * 
	 * @param p_strDate 字符型日期
	 * @param p_format 格式:"yyyy-MM-dd" / "yyyy-MM-dd hh:mm:ss"
	 * @return java.util.Date util.Date型日期
	 * @throws ParseException
	 */
	public static Date toUtilDateFromStrDateByFormat(String p_strDate, String p_format){
		java.util.Date l_date = null;
		java.text.DateFormat df = new java.text.SimpleDateFormat(p_format);
		if (p_strDate != null && (!"".equals(p_strDate)) && p_format != null && (!"".equals(p_format))) {
			try {
				l_date = df.parse(p_strDate);
			} catch (ParseException e) {
				return null;
			}
		}
		return l_date;
	}
    //计算指定时间距离当前时间差多少秒
    public static int getDatePoor(String endDate) {
    	Date tDate = toUtilDateFromStrDateByFormat(endDate, "yyyy-MM-dd hh:mm:ss");
    	if(tDate ==null)
    		return 0;
    	Date date = new Date();
        return (int)(tDate.getTime() - date.getTime())/1000;
    }
    public static boolean isTimeRange(Time time1, Time time2){
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Date now =null;
		try {
			now = df.parse(df.format(new Date()));
//			begin = df.parse(time1);
//	        end = df.parse(time2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Calendar nowTime = Calendar.getInstance();
        nowTime.setTime(now);
//        Calendar beginTime = Calendar.getInstance();
//        beginTime.setTime(begin);
//        Calendar endTime = Calendar.getInstance();
//        endTime.setTime(end);
        return nowTime.before(time1) && nowTime.after(time2);
    }
    
    /**
     * 判断当前时间是否在时间段内，不包括日期
     * @param start 超始时间
     * @param end 截止时间
     * @return
     */
    public static boolean inTimeRange(String start, String end) {
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		Date now = null;
		Date dt1=null,dt2=null;
		try {
			now = df.parse(df.format(new Date()));
			dt1 = df.parse(start);//将字符串转换为date类型
			dt2 =df.parse(end);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dt2.getTime() >now.getTime() && now.getTime() > dt1.getTime();
	}


}
