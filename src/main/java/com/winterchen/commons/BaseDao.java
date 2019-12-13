package com.winterchen.commons;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.winterchen.tools.SystemUtils;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <p>文件名称: BaseDao.java</p>
 * <p>文件描述: 本类为所有dao实现类的父类，实现通用增、删、改、查等操作，其中有没有sql语句变量和没有sql语句变量的方法，满足自定义sql语句的实现</p>
 * <p>完成日期：2015-11-11</p>
 * <p>修改记录0：无</p>
 * @param <E> 子类继承bean类型
 * @param <PK> 主键类型（String,Integer......）
 * @version 1.0
 * @author  wjk
 */
public class BaseDao<E, PK> extends SqlSessionDaoSupport implements
		Dao<E, PK> {

    Logger log = LoggerFactory.getLogger(this.getClass());
    Logger collectLog = LoggerFactory.getLogger("collectLogger");
    
	@Resource
	public void setSqlSessionTemplate(CustomSqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	public SqlSession getSqlSession(String type){
	    long beginTime = System.currentTimeMillis();
	    SqlSession sqlSession = super.getSqlSession();
	    long endTime = System.currentTimeMillis();
	    long time = endTime - beginTime;
	    if(time > Long.parseLong(DictParameter.getProperty("databaseWarn"))){
		String message = "";
		if(type.equals("read")){
		    message = "读数据库获取连接耗时："+time+"[ms]";
//		    log.warn(message);
		} else if(type.equals("write")){
		    message = "写数据库获取连接耗时："+time+"[ms]";
//		    log.warn(message);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", message);
		map.put("warn_type", "1");
		map.put("create_time", SystemUtils.getTimeForSql());
		map.put("dataSource_type", type);
		map.put("system_type", "interface");
		map.put("time", time);
		map.put("es_index", "earlywarning");
		map.put("es_type", "jkplatform");
		collectLog.info(JSONObject.fromObject(map).toString());
	    }
	    return sqlSession;
	}

	/**
	 * 查询条数（不带sql语句的）
	 */
	public Integer count(E bean) {
		return count(bean, getMybatisCountName());
	}

	/**
	 * 查询条数(带sql语句)
	 */
	public Integer count(E bean, String sqlKey) {
	    CustomerContextHolder.setContextType("mysql");
	    SqlSession session = getSqlSession("read");
	    long beginTime = System.currentTimeMillis();
	    Integer i = session.selectOne(sqlKey, bean);
	    long endTime = System.currentTimeMillis();
	    long time = endTime - beginTime;
	    if(time > Long.parseLong(DictParameter.getProperty("databaseWarn"))){
//		log.warn("sql执行耗时："+time+"[ms] "+sqlKey);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "sql执行耗时："+time+"[ms]");
		map.put("warn_type", "2");
		map.put("create_time", SystemUtils.getTimeForSql());
		map.put("dataSource_type", "read");
		map.put("system_type", "interface");
		map.put("sql_key", sqlKey);
		map.put("time", time);
		map.put("es_index", "earlywarning");
		map.put("es_type", "jkplatform");
		collectLog.info(JSONObject.fromObject(map).toString());
	    }
	    return i;
	}

	/**
	 * 根据主键删除相关信息
	 */
	public Integer delete(E bean) {
		return delete(bean, getMybatisDeleteName());
	}

	/**
	 * 根据传入的sql语句以及条件删除记录
	 */
	public Integer delete(E bean, String sqlKey) {
	    CustomerContextHolder.setContextType("mysql1");
	    SqlSession session = getSqlSession("write");
	    long beginTime = System.currentTimeMillis();
	    Integer i = session.delete(sqlKey, bean);
	    long endTime = System.currentTimeMillis();
	    long time = endTime - beginTime;
	    if(time > Long.parseLong(DictParameter.getProperty("databaseWarn"))){
//		log.warn("sql执行耗时："+time+"[ms]");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "sql执行耗时："+time+"[ms] "+sqlKey);
		map.put("warn_type", "2");
		map.put("create_time", SystemUtils.getTimeForSql());
		map.put("dataSource_type", "write");
		map.put("system_type", "interface");
		map.put("sql_key", sqlKey);
		map.put("time", time);
		map.put("es_index", "earlywarning");
		map.put("es_type", "jkplatform");
		collectLog.info(JSONObject.fromObject(map).toString());
	    }
	    return i;
	}

	/**
	 * 添加信息(带sql语句)
	 */
	public Integer insert(E enEity, String sqlKey) {
	    CustomerContextHolder.setContextType("mysql1");
	    SqlSession session = getSqlSession("write");
	    long beginTime = System.currentTimeMillis();
		//定义一个object类型的数据进行接收然后判断是否为空
		Object object = session.insert(sqlKey, enEity);
		long endTime = System.currentTimeMillis();
		long time = endTime - beginTime;
		if(time > Long.parseLong(DictParameter.getProperty("databaseWarn"))){
//		    log.warn("sql执行耗时："+time+"[ms] "+sqlKey);
		    Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "sql执行耗时："+time+"[ms]");
			map.put("warn_type", "2");
			map.put("create_time", SystemUtils.getTimeForSql());
			map.put("dataSource_type", "write");
			map.put("system_type", "interface");
			map.put("sql_key", sqlKey);
			map.put("time", time);
			map.put("es_index", "earlywarning");
			map.put("es_type", "jkplatform");
			collectLog.info(JSONObject.fromObject(map).toString());
		}
		if (object == null) {
			return null;
		} else {
			return (Integer) object;
		}
	}

	/**
	 * 添加信息(不带sql语句)
	 */
	public Integer insert(E enEity) {
		return insert(enEity, getMybatisInsertName());
	}

	/**
	 * 查询信息（带sql语句）
	 */
	@SuppressWarnings("unchecked")
	public List<E> select(E bean, String sqlKey) {
	    CustomerContextHolder.setContextType("mysql");
	    SqlSession session = getSqlSession("read");
	    long beginTime = System.currentTimeMillis();
	    List<E> list = (List<E>) session.selectList(sqlKey, bean);
	    long endTime = System.currentTimeMillis();
	    long time = endTime - beginTime;
	    if(time > Long.parseLong(DictParameter.getProperty("databaseWarn"))){
//		log.warn("sql执行耗时："+time+"[ms] "+sqlKey);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "sql执行耗时："+time+"[ms]");
		map.put("warn_type", "2");
		map.put("create_time", SystemUtils.getTimeForSql());
		map.put("dataSource_type", "read");
		map.put("system_type", "interface");
		map.put("sql_key", sqlKey);
		map.put("time", time);
		map.put("es_index", "earlywarning");
		map.put("es_type", "jkplatform");
		collectLog.info(JSONObject.fromObject(map).toString());
	    }
	    return list;
	}

	/**
	 * 查询信息（不带sql语句）
	 */
	public List<E> select(E bean) {
		return select(bean, getMybatisSelectName());
	}

	/**
	 * 根据主键查询详细信息（带sql语句）
	 */
	@SuppressWarnings("unchecked")
	public E selectPk(PK beanid, String sqlKey) {
	    CustomerContextHolder.setContextType("mysql");
	    SqlSession session = getSqlSession("read");
	    long beginTime = System.currentTimeMillis();
	    E object = (E) session.selectOne(sqlKey, beanid);
	    long endTime = System.currentTimeMillis();
	    long time = endTime - beginTime;
	    if(time > Long.parseLong(DictParameter.getProperty("databaseWarn"))){
//		log.warn("sql执行耗时："+time+"[ms] "+sqlKey);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "sql执行耗时："+time+"[ms]");
		map.put("warn_type", "2");
		map.put("create_time", SystemUtils.getTimeForSql());
		map.put("dataSource_type", "read");
		map.put("system_type", "interface");
		map.put("sql_key", sqlKey);
		map.put("time", time);
		map.put("es_index", "earlywarning");
		map.put("es_type", "jkplatform");
		collectLog.info(JSONObject.fromObject(map).toString());
	    }
	    return object;
	}

	/**
	 * 根据主键查询详细信息（不带sql语句）
	 */
	public E selectPk(PK beanid) {
		return selectPk(beanid, getMybatisSelectByPkName());
	}
	
	/**
	 * 根据bean查询详细信息
	 */
	@SuppressWarnings("unchecked")
	public E selectBean(E bean, String sqlKey){
	    CustomerContextHolder.setContextType("mysql");
	    SqlSession session = getSqlSession("read");
	    long beginTime = System.currentTimeMillis();
	    E object = (E) session.selectOne(sqlKey, bean);
	    long endTime = System.currentTimeMillis();
	    long time = endTime - beginTime;
	    if(time > Long.parseLong(DictParameter.getProperty("databaseWarn"))){
//		log.warn("sql执行耗时："+time+"[ms] "+sqlKey);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "sql执行耗时："+time+"[ms]");
		map.put("warn_type", "2");
		map.put("create_time", SystemUtils.getTimeForSql());
		map.put("dataSource_type", "read");
		map.put("system_type", "interface");
		map.put("sql_key", sqlKey);
		map.put("time", time);
		map.put("es_index", "earlywarning");
		map.put("es_type", "jkplatform");
		collectLog.info(JSONObject.fromObject(map).toString());
	    }
	    return object;
	}
	
	/**
	 * 根据bean查询详细信息
	 */
	public E selectBean(E bean){
	    return selectBean(bean, getMybatisSelectBeanName());
	}

	/**
	 * 根据传入的map查询相关信息
	 */
	@SuppressWarnings("unchecked")
	public List<E> select(Map<String, Object> queryMap, String sqlKey) {
	    CustomerContextHolder.setContextType("mysql");
	    SqlSession session = getSqlSession("read");
	    long beginTime = System.currentTimeMillis();
	    List<E> list = (List<E>) session.selectList(sqlKey,queryMap);
	    long endTime = System.currentTimeMillis();
	    long time = endTime - beginTime;
	    if(time > Long.parseLong(DictParameter.getProperty("databaseWarn"))){
//		log.warn("sql执行耗时："+time+"[ms] "+sqlKey);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "sql执行耗时："+time+"[ms]");
		map.put("warn_type", "2");
		map.put("create_time", SystemUtils.getTimeForSql());
		map.put("dataSource_type", "read");
		map.put("system_type", "interface");
		map.put("sql_key", sqlKey);
		map.put("time", time);
		map.put("es_index", "earlywarning");
		map.put("es_type", "jkplatform");
		collectLog.info(JSONObject.fromObject(map).toString());
	    }
	    return list;
	}

	/**
	 * 修改相关信息（带sql语句）
	 */
	public Integer update(E bean, String sqlKey) {
	    CustomerContextHolder.setContextType("mysql1");
	    SqlSession session = getSqlSession("write");
	    long beginTime = System.currentTimeMillis();
	    Integer i = session.update(sqlKey, bean);
	    long endTime = System.currentTimeMillis();
	    long time = endTime - beginTime;
	    if(time > Long.parseLong(DictParameter.getProperty("databaseWarn"))){
//		log.warn("sql执行耗时："+time+"[ms] "+sqlKey);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "sql执行耗时："+time+"[ms]");
		map.put("warn_type", "2");
		map.put("create_time", SystemUtils.getTimeForSql());
		map.put("dataSource_type", "write");
		map.put("system_type", "interface");
		map.put("sql_key", sqlKey);
		map.put("time", time);
		map.put("es_index", "earlywarning");
		map.put("es_type", "jkplatform");
		collectLog.info(JSONObject.fromObject(map).toString());
	    }
	    return i;
	}

	/**
	 * 修改相关信息（不带sql语句）
	 */
	public Integer update(E bean) {
		return update(bean, getMybatisUpdateName());
	}

	/**
	 * 分页查询（带sql语句的）
	 */
	@SuppressWarnings("unchecked")
	public List<E> select(E bean, String sqlKey, Page<BaseBean> page) {
	    CustomerContextHolder.setContextType("mysql");
	    SqlSession session = getSqlSession("read");
	    long beginTime = System.currentTimeMillis();
	    List<E> list = null;
	    if(page != null){
		if(page.getOrderBy() != null){
		    PageHelper.orderBy(page.getOrderBy());
		}
		list = session.selectList(sqlKey, bean, new RowBounds(page.getPageNum(),page.getPageSize()));
	    } else {
		list = (List<E>) session.selectList(sqlKey, bean);
	    }
	    long endTime = System.currentTimeMillis();
	    long time = endTime - beginTime;
	    if(time > Long.parseLong(DictParameter.getProperty("databaseWarn"))){
//		log.warn("sql执行耗时："+time+"[ms] "+sqlKey);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", "sql执行耗时："+time+"[ms]");
		map.put("warn_type", "2");
		map.put("create_time", SystemUtils.getTimeForSql());
		map.put("dataSource_type", "read");
		map.put("system_type", "interface");
		map.put("sql_key", sqlKey);
		map.put("time", time);
		map.put("es_index", "earlywarning");
		map.put("es_type", "jkplatform");
		collectLog.info(JSONObject.fromObject(map).toString());
	    }
	    return list;
	}


	public List<Map<String, Object>> selectMap(E bean, String sqlKey, Page<BaseBean> page) {
		CustomerContextHolder.setContextType("mysql");
		SqlSession session = getSqlSession("read");
		long beginTime = System.currentTimeMillis();
		List<Map<String, Object>> list = null;
		if(page != null){
			if(page.getOrderBy() != null){
				PageHelper.orderBy(page.getOrderBy());
			}
			list = session.selectList(sqlKey, bean, new RowBounds(page.getPageNum(),page.getPageSize()));
		} else {
			list = session.selectList(sqlKey, bean);
		}
		long endTime = System.currentTimeMillis();
		long time = endTime - beginTime;
		if(time > Long.parseLong(DictParameter.getProperty("databaseWarn"))){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "sql执行耗时："+time+"[ms]");
			map.put("warn_type", "2");
			map.put("create_time", SystemUtils.getTimeForSql());
			map.put("dataSource_type", "read");
			map.put("system_type", "interface");
			map.put("sql_key", sqlKey);
			map.put("time", time);
			map.put("es_index", "earlywarning");
			map.put("es_type", "jkplatform");
			collectLog.info(JSONObject.fromObject(map).toString());
		}
		return list;
	}

	/**
	 * 分页查询（不带sql语句的）
	 */
	public List<E> select(E bean, Page<BaseBean> page) {
	    if(page != null){
		return select(bean, getMybatisSelectName(), page);
	    } else {
		return select(bean);
	    }
	    
	}

	public String getMybatisNameSpace() {
		throw new RuntimeException("not yet implement");
	}

	public String getMybatisInsertName() {
		return getMybatisNameSpace() + ".insert";
	}

	public String getMybatisUpdateName() {
		return getMybatisNameSpace() + ".update";
	}

	public String getMybatisCountName() {
		return getMybatisNameSpace() + ".count";
	}

	public String getMybatisSelectName() {
		return getMybatisNameSpace() + ".select";
	}

	public String getMybatisSelectByPkName() {
		return getMybatisNameSpace() + ".selectByPK";
	}

	public String getMybatisDeleteName() {
		return getMybatisNameSpace() + ".delete";
	}
	
	public String getMybatisSelectBeanName() {
		return getMybatisNameSpace() + ".selectBean";
	}

}
