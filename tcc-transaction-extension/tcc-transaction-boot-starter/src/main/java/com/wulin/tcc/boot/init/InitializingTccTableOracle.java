package com.wulin.tcc.boot.init;

import com.wulin.tcc.boot.util.JdbcConstants;

/**
 * 初始化tcc的事务表,oracle实现
 * @author wulin
 *
 */
public class InitializingTccTableOracle extends AbstractInitializingTccTable{

	@Override
	public String databaseType() {
		return JdbcConstants.ORACLE;
	}
	
	protected String getCreateTabSql(String tableName,String tbSuffix) {
		return " CREATE TABLE "+tableName+" ( "
			  +" TRANSACTION_ID number(11)  primary key not null , "
			  +" DOMAIN varchar2(100) DEFAULT NULL, "
			  +" GLOBAL_TX_ID varchar2(32) NOT NULL, "
			  +" BRANCH_QUALIFIER varchar2(32) NOT NULL, "
			  +" CONTENT blob DEFAULT NULL, "
			  +" STATUS number(11) DEFAULT NULL, "
			  +" TRANSACTION_TYPE number(11) DEFAULT NULL, "
			  +" RETRIED_COUNT number(11) DEFAULT NULL, "
			  +" CREATE_TIME date DEFAULT NULL, "
			  +" LAST_UPDATE_TIME date DEFAULT NULL, "
			  +" VERSION number(11) DEFAULT NULL, "
			  +" constraint UX_TX_BQ"+tbSuffix+" unique(GLOBAL_TX_ID,BRANCH_QUALIFIER) "
			  +" ) ";
	}
}


