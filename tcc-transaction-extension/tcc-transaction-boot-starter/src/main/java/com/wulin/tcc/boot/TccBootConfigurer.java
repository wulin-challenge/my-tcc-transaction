package com.wulin.tcc.boot;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.StringUtils;

import com.wulin.tcc.boot.init.InitializingTccTable;
import com.wulin.tcc.boot.properties.TccProperties;
import com.wulin.tcc.boot.util.JdbcUtils;

/**
 * tcc 集成spring boot 的配置
 * @author wulin
 *
 */
public class TccBootConfigurer implements BeanPostProcessor,BeanFactoryAware{
	private static final Log logger = LogFactory.getLog(TccBootConfigurer.class);
	
	private TccProperties tccProperties;
	
	private DefaultListableBeanFactory beanFactory;
	
	/**
	 * 已经初始化的DataSource
	 */
	private Set<String> alreadyInitDataSource = new HashSet<String>();

	public TccBootConfigurer(TccProperties tccProperties) {
		super();
		this.tccProperties = tccProperties;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(bean instanceof DataSource) {
			initTccTable((DataSource)bean,beanName);
		}
		return bean;
	}
	
	/**
	 * 初始tcc表
	 */
	private void initTccTable(DataSource dataSource,String beanName) {
		try {
			String url = dataSource.getConnection().getMetaData().getURL();
			
			if(StringUtils.isEmpty(url) || StringUtils.isEmpty(JdbcUtils.getDbType(url))) {
				throw new SQLException("数据源的url为空或者没有这样的数据库类型!");
			}
			
			if(alreadyInitDataSource.contains(url)) {
				return;
			}
			
			Collection<InitializingTccTable> tccTableList = beanFactory.getBeansOfType(InitializingTccTable.class).values();
			for (InitializingTccTable initializingTccTable : tccTableList) {
				if(JdbcUtils.getDbType(url).equals(initializingTccTable.databaseType())) {
					initializingTccTable.createTccTable(dataSource,tccProperties);
					alreadyInitDataSource.add(url);
					break;
				}
			}
		} catch (SQLException e) {
			logger.error("初始化tcc的相关表出错,具体错误:",e);
		}
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = (DefaultListableBeanFactory) beanFactory;
	}
}
