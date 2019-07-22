package com.wulin.boot.simple.common.init.sqlscript;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.SmartLifecycle;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.util.StringUtils;

/**
 * 初始话SQL脚本
 * @author wulin
 *
 */
public class InitSqlScriptConfigurer implements SmartLifecycle,BeanFactoryAware{
	
	/**
	 * 初始化放在 classpath路径下的init_sql 目录下的sql文件的路径表达式
	 */
	private static final String CLASSPATH_POSITION_PATTERN = "classpath:init_sql/*.sql";
	
	/**
	 * 初始化放在 user.dir路径下的init_sql 目录下的sql文件的路径表达式
	 */
	private static final String USERDIR_POSITION_PATTERN = "file:"+System.getProperty("user.dir")+"/init_sql/*.sql";
	
	private Log log = LogFactory.getLog(InitSqlScriptConfigurer.class);

	private SqlScriptProperties sqlScriptProperties;
	
	private DefaultListableBeanFactory beanFactory;
	
	private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
	
	/**
	 * 是否正在运行
	 */
	private boolean running = false;

	public InitSqlScriptConfigurer(SqlScriptProperties sqlScriptProperties) {
		super();
		this.sqlScriptProperties = sqlScriptProperties;
	}
	
	@Override
	public void start() {
		log.info("开始初始化sql脚本!");
		// 如果已经启动，则抛出 IllegalStateException 异常
        if (this.isRunning()) {
            throw new IllegalStateException("sql script already running. " + this.toString());
        }
        Map<String, DataSource> dataSourceBean = beanFactory.getBeansOfType(DataSource.class);
        if(dataSourceBean == null || dataSourceBean.isEmpty()) {
        	throw new IllegalStateException("当前应用程序没有数据源 DataSource .");
        }
        
        Collection<DataSource> dataSources = dataSourceBean.values();
        //执行初始化sql
        executeInitializingSql(dataSources);
		setRunning(true);
		log.info("结束初始化sql脚本!");
	}
	
	/**
	 * 执行初始化sql
	 */
	private void executeInitializingSql(Collection<DataSource> dataSources) {
		for (DataSource dataSource : dataSources) {
			String sqlPosition = sqlScriptProperties.getSqlPosition();
			if(SqlScriptProperties.CLASSPATH_INIT_SQL.equals(sqlPosition)) {
				
				executeInitializingSql(replace(CLASSPATH_POSITION_PATTERN),dataSource);
			}else if(SqlScriptProperties.USER_DIR_INIT_SQL.equals(sqlPosition)) {
				
				executeInitializingSql(replace(USERDIR_POSITION_PATTERN),dataSource);
			}else {
				throw new IllegalArgumentException("sql-position的值不正确!正取值为classpath/userdir,温馨提示:SQL文件是放在对应的init_sql目录下的.");
			}
		}
	}
	
	private void executeInitializingSql(String positionPattern,DataSource dataSources) {
		String sqlPath = "";
		try(Connection conn = dataSources.getConnection()){
			Resource[] resources = resourcePatternResolver.getResources(positionPattern);
			for (Resource resource : sortResources(resources)) {
				if(resource.getFile().length() == 0) {
					continue;
				}
				sqlPath = resource.getFile().getAbsolutePath();
				EncodedResource er = new EncodedResource(resource, "UTF-8");
				ScriptUtils.executeSqlScript(conn, er); //要源码来解决 日期问题,源码很简单的
			}
		} catch (SQLException e) {
			log.error("SQL脚本执行错误,SQL路径:"+sqlPath, e);
		} catch (IOException e) {
			log.error("获取SQL脚本资源出错,SQL路径:"+sqlPath, e);
		} catch (Exception e) {
			log.error("SQL脚本出错,SQL路径:"+sqlPath, e);
		}
	}
	
	/**
	 * 排序资源
	 * @param resources
	 * @return
	 */
	private List<Resource> sortResources(Resource[] resources){
		List<Resource> list = new ArrayList<Resource>();
		if(resources == null || resources.length == 0) {
			log.error("没有找到要初始化的SQL文件!");
			return list;
		}
		list.addAll(Arrays.asList(resources));
		list.sort((x,y)->{
			return getResourceSortNumber(x)-getResourceSortNumber(y);
		});
		return list;
	}
	
	/**
	 * 得到资源排序值
	 * @param resource
	 * @return
	 */
	private int getResourceSortNumber(Resource resource) {
		String filename = resource.getFilename();
		if(StringUtils.isEmpty(filename) || !filename.contains("_")) {
			return 0;
		}
		
		String number = filename.substring(filename.lastIndexOf("_")+1,filename.lastIndexOf("."));
		if(!StringUtils.isEmpty(number)) {
			try {
				return Integer.parseInt(number);
			} catch (NumberFormatException e) {
				log.warn("文件取值排序失败,文件名:"+filename);
			}
		}
		return 0;
	}

	@Override
	public void stop() {
		 // 必须在运行中
        if (this.isRunning()) {
            // 标记不在启动
            setRunning(false);
        }
	}

	@Override
	public boolean isRunning() {
		return this.running;
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}

	@Override
	public int getPhase() {
		// Returning Integer.MAX_VALUE only suggests that
        // we will be the first bean to shutdown and last bean to start
        return Integer.MAX_VALUE;
	}

	@Override
	public boolean isAutoStartup() {
		return sqlScriptProperties.getRunning();
	}

	@Override
	public void stop(Runnable callback) {
		stop();
		callback.run();
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = (DefaultListableBeanFactory) beanFactory;
	}
	
	private static String replace(String s){
		s = s.replace("\\", "/");
		s = s.replace("\\\\", "/");
		return s;
	}
}
