package com.wulin.tcc.boot.init;

import com.wulin.tcc.boot.util.JdbcConstants;

/**
 * 初始化tcc的事务表,mysql实现
 * @author wulin
 *
 */
public class InitializingTccTableMysql extends AbstractInitializingTccTable{
	
	@Override
	public String databaseType() {
		return JdbcConstants.MYSQL;
	}
	
	protected String getCreateTabSql(String tableName,String tbSuffix) {
		String sql = "CREATE TABLE `"+tableName+"` (\r\n" + 
				"  `TRANSACTION_ID` int(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `DOMAIN` varchar(100) DEFAULT NULL,\r\n" + 
				"  `GLOBAL_TX_ID` varbinary(32) NOT NULL,\r\n" + 
				"  `BRANCH_QUALIFIER` varbinary(32) NOT NULL,\r\n" + 
				"  `CONTENT` varbinary(8000) DEFAULT NULL,\r\n" + 
				"  `STATUS` int(11) DEFAULT NULL,\r\n" + 
				"  `TRANSACTION_TYPE` int(11) DEFAULT NULL,\r\n" + 
				"  `RETRIED_COUNT` int(11) DEFAULT NULL,\r\n" + 
				"  `CREATE_TIME` datetime DEFAULT NULL,\r\n" + 
				"  `LAST_UPDATE_TIME` datetime DEFAULT NULL,\r\n" + 
				"  `VERSION` int(11) DEFAULT NULL,\r\n" + 
				"  PRIMARY KEY (`TRANSACTION_ID`),\r\n" + 
				"  UNIQUE KEY `UX_TX_BQ"+tbSuffix+"` (`GLOBAL_TX_ID`,`BRANCH_QUALIFIER`)\r\n" + 
				") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		return sql;
	}
}
