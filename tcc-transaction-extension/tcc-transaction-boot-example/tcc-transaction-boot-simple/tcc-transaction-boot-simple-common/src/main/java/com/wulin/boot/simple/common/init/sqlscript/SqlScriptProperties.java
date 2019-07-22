package com.wulin.boot.simple.common.init.sqlscript;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * sql脚本配置项
 * @author wulin
 *
 */
@Configuration
@ConfigurationProperties(prefix="tcc.sql-script",ignoreUnknownFields=true)
public class SqlScriptProperties {
	/**
	 * 放在 classpath路径下的init_sql 目录的sql文件
	 */
	public static final String CLASSPATH_INIT_SQL = "classpath";
	
	/**
	 * 放在user.dir路径下的init_sql 目录的sql文件
	 */
	public static final String USER_DIR_INIT_SQL = "userdir";
	
	/**
	 * 是否运行脚本
	 */
	private boolean running = false;
	
	/**
	 * SQL文件的位置,若执行的SQL文件有先后顺序,这文件必须以  XXX_num.sql的格式命名,XXX代表SQL文件,_num代表SQL的执行顺序
	 */
	private String sqlPosition = CLASSPATH_INIT_SQL;

	public boolean getRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public String getSqlPosition() {
		return sqlPosition;
	}

	public void setSqlPosition(String sqlPosition) {
		this.sqlPosition = sqlPosition;
	}
}
