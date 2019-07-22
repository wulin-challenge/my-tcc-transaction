package com.wulin.tcc.boot.init;

import javax.sql.DataSource;

import com.wulin.tcc.boot.properties.TccProperties;

/**
 * 初始化tcc表
 * @author wulin
 *
 */
public interface InitializingTccTable{
	
	/**
	 * 创建tcc相关的表,如果没有的情况下
	 * @param dataSource
	 */
	void createTccTable(DataSource dataSource,TccProperties tccProperties);
	
	/**
	 * 得到数据库类型,数据库类型来自于 JdbcConstants类
	 * @return
	 */
	String databaseType();
}

