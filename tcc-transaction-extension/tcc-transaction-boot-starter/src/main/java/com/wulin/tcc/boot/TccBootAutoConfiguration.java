package com.wulin.tcc.boot;

import javax.sql.DataSource;

import org.mengyun.tcctransaction.TransactionRepository;
import org.mengyun.tcctransaction.spring.recover.DefaultRecoverConfig;
import org.mengyun.tcctransaction.spring.repository.SpringJdbcTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.util.StringUtils;

import com.wulin.tcc.boot.init.InitializingTccTable;
import com.wulin.tcc.boot.init.InitializingTccTableMysql;
import com.wulin.tcc.boot.init.InitializingTccTableOracle;
import com.wulin.tcc.boot.properties.TccProperties;

/**
 * tcc的自动配置
 * @author wulin
 *
 */
@Configuration
@EnableConfigurationProperties(TccProperties.class)
@ImportResource(value= {"classpath:tcc-transaction.xml"})
public class TccBootAutoConfiguration {

	@Autowired
	private TccProperties tccProperties;
	
	@Bean
	@ConditionalOnMissingBean
	public DefaultRecoverConfig defaultRecoverConfig() {
		DefaultRecoverConfig defaultRecoverConfig = new DefaultRecoverConfig();
		defaultRecoverConfig.setMaxRetryCount(tccProperties.getRecover().getMaxRetryCount());
		defaultRecoverConfig.setCronExpression(tccProperties.getRecover().getCronExpression());
		defaultRecoverConfig.setRecoverDuration(tccProperties.getRecover().getRecoverDuration());
		defaultRecoverConfig.setAsyncTerminateThreadPoolSize(tccProperties.getRecover().getAsyncTerminateThreadPoolSize());
		return defaultRecoverConfig;
	}
	
	@Bean
	@ConditionalOnProperty(prefix="tcc.repository",name="type",havingValue="jdbc",matchIfMissing=true)
	public TransactionRepository transactionRepository(DataSource dataSource) {
		String tbSuffix = tccProperties.getRepository().getTbSuffix();
		tbSuffix = StringUtils.isEmpty(tbSuffix) || tbSuffix.startsWith("_")?tbSuffix:"_"+tbSuffix;
		tbSuffix = tbSuffix.trim().toUpperCase();
		
		SpringJdbcTransactionRepository transaction = new SpringJdbcTransactionRepository();
		transaction.setDataSource(dataSource);
		transaction.setDomain(tccProperties.getRepository().getDomain());
		transaction.setTbSuffix(tbSuffix);
		return transaction;
	}
	
	@Bean
	@ConditionalOnMissingBean
	public TccBootConfigurer TccBootConfigurer() {
		TccBootConfigurer tccBootConfigurer = new TccBootConfigurer(tccProperties);
		return tccBootConfigurer;
	}
	
	@Bean(name="initializingTccTableMysql")
	@ConditionalOnMissingBean(name="initializingTccTableMysql")
	public InitializingTccTable initializingTccTableMysql() {
		InitializingTccTableMysql initializingTcc = new InitializingTccTableMysql();
		return initializingTcc;
	}
	
	@Bean(name="initializingTccTableOracle")
	@ConditionalOnMissingBean(name="initializingTccTableOracle")
	public InitializingTccTable initializingTccTableOracle() {
		InitializingTccTableOracle initializingTcc = new InitializingTccTableOracle();
		return initializingTcc;
	}
}
