package com.wulin.tcc.boot.dubbo.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * 代理使用 @Reference 注解应用的bean
 * @author wulin
 *
 */
public class DubboReferenceFactoryBean implements FactoryBean<Object>{
	
	private Class<?> beanClass;
	
	public DubboReferenceFactoryBean(Class<?> beanClass) {
		super();
		this.beanClass = beanClass;
	}

	@Override
	public Object getObject() throws Exception {
		return DubboReferenceBeanFactory.getBean(beanClass);
	}

	@Override
	public Class<?> getObjectType() {
		return beanClass;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
