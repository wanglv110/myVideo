package com.winterchen.commons;

import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * 
 * <p>文件名称: Dao.java</p>
 * <p>文件描述: 此类为所有dao接口的父类，定义了通用增、删、改、查方法</p>
 * <p>完成日期：2015-11-11</p>
 * <p>修改记录0：无</p>
 * @param <E> 继承bean类型
 * @param <PK> 主键类型
 * @version 1.0
 * @author  wjk
 */
public interface Dao<E, PK> {
	
	/**
	 * 获得namespace名称，用于获得Mybatis空间名称的获取
	 * @return String 返回Mybatis空间名称
	 */
	public String getMybatisNameSpace();
	
	
	/**
	 * 带sql语句的添加方法，根据用户传入的sql语句名称和bean变量，添加一条新的记录
	 * @param bean 传入的bean类
	 * @param sqlKey 要执行的sql语句
	 */
	public Integer insert(E bean, String sqlKey);
	
	/**
	 * 不带sql语句的添加方法，根据用户传入的bean类变量默认调用Mybatis中insert方法添加一条记录
	 * @param bean 传入的bean类
	 */
	public Integer insert(E bean);

	/**
	 * 带sql语句的修改方法，根据用户传入的sql语句名称和bean变量，修改记录，返回修改的记录数
	 * @param bean 传入的bean类
	 * @param sqlKey 要执行的sql语句
	 * @return Integer 修改的记录的条数
	 */
	public Integer update(E bean, String sqlKey);
	
	/**
	 * 不带sql语句的修改方法，根据用户传入的bean类变量默认调用Mybatis中update方法修改记录，返回修改的记录数
	 * @param bean 传入的bean类
	 * @return Integer 修改的记录的条数
	 */
	public Integer update(E bean);
	
	/**
	 * 根据id删除（传入的参数为Bean），根据传入的bean类中的主键删除一条记录默认调用Mybatis中delete方法，返回删除记录数
	 * @param bean 传入的bean类
	 * @return Integer 删除记录的条数
	 */
	public Integer delete(E bean);

	/**
	 * 传sql语句的删除方法，根据出入的sql语句以及，条件bean类删除对应记录，返回删除的记录数
	 * @param bean 传入的bean类
	 * @param sqlKey 要执行的sql语句
	 * @return Integer 删除记录的条数
	 */
	public Integer delete(E bean, String sqlKey);

	/**
	 * 带sql语句的查询（不分页），根据出入的sql语句以及bean类变量查询要对应bean类的结果集List
	 * @param bean 传入的bean类
	 * @param sqlKey 要执行的sql语句
	 * @return List<E> 查询到的结果集
	 */
	public List<E> select(E bean, String sqlKey);

	/**
	 * 不带sql语句的查询（不分页）,根据出入的bean类变量以及默认的Mybatis的select方法查询相应信息，返回查询结果集List
	 * @param bean 传入的bean类
	 * @return List<E> 查询到的结果集
	 */
	public List<E> select(E bean);

	/**
	 * 带sql语句的查询（分页），根据出入的bean类以及sql语句和分页对象page，分会分页查询结果集List
	 * @param bean 传入的bean类
	 * @param sqlKey 要执行的sql语句
	 * @param page 传入的分页对象
	 * @return List<E> 查询到的结果集
	 */
	public List<E> select(E bean, String sqlKey, Page<BaseBean> page);

	/**
	 * 带sql语句的查询（分页），根据出入的bean类以及sql语句和分页对象page，分会分页查询结果集List
	 * @param bean 传入的bean类
	 * @param sqlKey 要执行的sql语句
	 * @param page 传入的分页对象
	 * @return List<E> 查询到的结果集
	 */
	public List<Map<String, Object>> selectMap(E bean, String sqlKey, Page<BaseBean> page);

	/**
	 * 不带sql语句的分页查询，根据出入的bean类和分页对象page，默认为Mybatis的select方法查询相应信息，返回分页查询结果集List
	 * @param bean 传入的bean类
	 * @param page 传入的分页对象
	 * @return List<E> 查询到的结果集
	 */
	public List<E> select(E bean, Page<BaseBean> page);

	/**
	 * 带sql语句根据主键查询详细信息，根据传入的主键以及sql语句，查询相应详细信息放入相应bean类对象返回，
	 * @param beanid 传入的主键
	 * @param sqlKey 要执行的sql语句
	 * @return bean 查询到的结果bean
	 */
	public E selectPk(PK beanid, String sqlKey);

	/**
	 * 不带sql语句根据主键查询详细信息，根据出入的主键以及默认的Mybatis中selectByPk查询详细信息放入相应的bean类对象返回
	 * @param beanid 传入的主键
	 * @return bean 查询到的结果bean
	 */
	public E selectPk(PK beanid);

	/**
	 * 带sql语句根据主键查询详细信息，根据传入的bean以及sql语句，查询相应详细信息放入相应bean类对象返回，
	 * @param bean 传入的bean类
	 * @param sqlKey 要执行的sql语句
	 * @return bean 查询到的结果bean
	 */
	public E selectBean(E bean, String sqlKey);

	/**
	 * 带sql语句根据主键查询详细信息，根据传入的bean以及默认的语句，查询相应详细信息放入相应bean类对象返回，
	 * @param bean 传入的bean类
	 * @return bean 查询到的结果bean
	 */
	public E selectBean(E bean);

	/**
	 * 根据传入的Map以及sql语句返回相应的结果集List，
	 * @param queryMap 传入的变量Map
	 * @param sqlKey   要执行的sql语句
	 * @return List<E> 返回的结果集
	 */
	public List<E> select(Map<String, Object> queryMap, String sqlKey);

	/**
	 * 查询总记录数，根据传入的bean类对象以及默认Mybatis中count方法，返回对应记录数
	 * @param bean 传入的bean类
	 * @return Integer 返回的记录数
	 */
	public Integer count(E bean);

	/**
	 * 查询总记录数(带sql语句)，根据传入的bean类对象以及sql语句查询，返回对应记录数
	 * @param bean 传入的bean类
	 * @return Integer 返回的记录数
	 */
	public Integer count(E bean, String sqlKey);
}

