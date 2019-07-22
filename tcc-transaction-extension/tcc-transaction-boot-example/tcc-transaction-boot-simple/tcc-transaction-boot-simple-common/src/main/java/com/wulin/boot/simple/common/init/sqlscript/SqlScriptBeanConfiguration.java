package com.wulin.boot.simple.common.init.sqlscript;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * sql脚本bean的配置
 * @author wulin
 *
 */
@Configuration
@EnableConfigurationProperties(SqlScriptProperties.class)
public class SqlScriptBeanConfiguration {
	
	@Bean
	@ConditionalOnProperty(prefix="tcc.sql-script",name="running",havingValue="true")
	public InitSqlScriptConfigurer initSqlScriptConfigurer(SqlScriptProperties sqlScriptProperties) {
		InitSqlScriptConfigurer initSqlScriptBean = new InitSqlScriptConfigurer(sqlScriptProperties);
		return initSqlScriptBean;
	}

}
