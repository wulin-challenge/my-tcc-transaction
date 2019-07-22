package com.wulin.tcc.boot.dubbo.bean;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 存储 @Reference注入的duboo代理对象工厂
 * @author wulin
 *
 */
public class DubboReferenceBeanFactory {
	
	/**
	 * 自定义bean存储的工厂,只能存储单例bean
	 */
	private static final ConcurrentHashMap<Class<?>,Object> beanFactory = new ConcurrentHashMap<Class<?>,Object>();

	public static ConcurrentHashMap<Class<?>, Object> getBeanfactory() {
		return beanFactory;
	}
	
	/**
	 * 获取bean
	 * @param clazz
	 * @return
	 */
	public static Object getBean(Class<?> clazz) {
		return getBeanfactory().get(clazz);
	}
	
	/**
	 * 注册bean
	 * @param clazz
	 * @param bean
	 */
	public static void registryBean(Class<?> clazz,Object bean) {
		getBeanfactory().put(clazz, bean);
	}
}
