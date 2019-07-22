package com.wulin.tcc.boot.init;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.wulin.tcc.boot.properties.TccProperties;
import com.wulin.tcc.boot.util.JdbcUtils;

public abstract class AbstractInitializingTccTable implements InitializingTccTable{
	private static final Log logger = LogFactory.getLog(AbstractInitializingTccTable.class);
	
	@Override
	public void createTccTable(DataSource dataSource, TccProperties tccProperties) {
		String tbSuffix = tccProperties.getRepository().getTbSuffix();
		tbSuffix = StringUtils.isEmpty(tbSuffix) || tbSuffix.startsWith("_")?tbSuffix:"_"+tbSuffix;
		tbSuffix = tbSuffix.trim().toUpperCase();
		
		String tableName = "TCC_TRANSACTION"+tbSuffix;
		tableName = tableName.trim().toUpperCase();
		
		try (Connection connection = dataSource.getConnection();){
			ResultSet tables = connection.getMetaData().getTables(connection.getCatalog(), null, tableName, null);
			if(tables ==null || !tables.next()) {
				String createTabSql = getCreateTabSql(tableName,tbSuffix);
				JdbcUtils.execute(connection, createTabSql);
				logger.info("初始化tcc的事务表成功,表名称: "+tableName);
			}
		} catch (SQLException e) {
			logger.error("创建  "+tableName+" 失败!,"+e.getMessage());
		}
	}
	
	/**
	 * 得到创建表语句
	 * @return
	 */
	protected abstract String getCreateTabSql(String tableName,String tbSuffix);
}
