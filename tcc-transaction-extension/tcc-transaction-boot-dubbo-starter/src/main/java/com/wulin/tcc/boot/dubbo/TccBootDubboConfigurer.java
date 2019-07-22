package com.wulin.tcc.boot.dubbo;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.config.AopConfigUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.SmartLifecycle;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wulin.tcc.boot.dubbo.bean.AopTargetUtil;
import com.wulin.tcc.boot.dubbo.bean.DubboReferenceBeanFactory;
import com.wulin.tcc.boot.dubbo.bean.DubboReferenceFactoryBean;

public class TccBootDubboConfigurer implements SmartLifecycle,BeanFactoryAware,BeanPostProcessor{
	
	private static final Log logger = LogFactory.getLog(TccBootDubboConfigurer.class);
	
	private DefaultListableBeanFactory beanFactory;
	
	/**
	 * 使用@Reference注入的bean名称
	 */
	private Set<String> beanNames = Collections.synchronizedSet(new HashSet<String>());
	/**
	 * 是否正在运行
	 */
	private boolean running = false;
	
	public Object postProcessBeforeInitialization(Object bean, String beanName)throws BeansException {
		Class<?> clazz = AopUtils.getTargetClass(bean);
		operationField(bean, beanName, clazz,new ReferenceField() {
			public void refer(Object bean, String beanName, Field field) throws Exception{
				beanNames.add(beanName);
			}
		});
        return bean;
    }
	
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public void start() {
		for (String beanName : beanNames) {
			Object bean = beanFactory.getBean(beanName);
			// 获得 Bean 对应的 Class 类名。因为有可能被 AOP 代理过。
            Class<?> clazz = AopUtils.getTargetClass(bean);
            bean = AopTargetUtil.getTarget(bean);
            if(bean == null) {
            	continue;
            }
			operationField(bean, beanName, clazz,new ReferenceField() {
				public void refer(Object bean, String beanName, Field field) throws Exception {
					Object referenceValue = field.get(bean);
					Class<?> referenceClass = field.getType();
					
					if(referenceValue == null) {
						return;
					}
					
					String proxyBeanName = createBean(referenceValue, referenceClass);
					Object proxyBean = beanFactory.getBean(proxyBeanName);
					field.set(bean, proxyBean);
				}
			});
		}
		
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
        return Integer.MAX_VALUE-2;
	}

	@Override
	public boolean isAutoStartup() {
		return true;
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
	
	/**
	 * 创建代理
	 * @param beanName
	 * @param bean
	 */
	private void createProxy(String beanName,Object bean) {
		AnnotationAwareAspectJAutoProxyCreator proxyCreatorBean = (AnnotationAwareAspectJAutoProxyCreator) beanFactory.getBean(AopConfigUtils.AUTO_PROXY_CREATOR_BEAN_NAME);
		proxyCreatorBean.postProcessAfterInitialization(bean, beanName);
	}
	
	/**
	 * 创建bean,并返回bean的名称
	 * @param bean
	 * @param beanClass
	 * @return
	 */
	private String createBean(Object bean,Class<?> beanClass) {
		String beanName = beanClass.getName();
		
		if(beanFactory.containsBean(beanName)) {
			return beanName;
		}
		
		DubboReferenceBeanFactory.registryBean(beanClass, bean);
		
		GenericBeanDefinition bd = new GenericBeanDefinition();
		bd.setBeanClass(DubboReferenceFactoryBean.class);
		//设置目标bean的class
		bd.getConstructorArgumentValues().addGenericArgumentValue(beanClass); // issue #59
		bd.setAutowireMode(AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE);
		
		beanFactory.registerBeanDefinition(beanName, bd);
		createProxy(beanName, bean);
		return beanName;
	}
	
	/**
	 * 操作属性
	 * @param bean
	 * @param beanName
	 * @param referenceField
	 */
	private void operationField(Object bean, String beanName,Class<?> clazz,ReferenceField referenceField) {
		 Field[] fields = clazz.getDeclaredFields();
	        for (Field field : fields) {
	            try {
	                if (!field.isAccessible()) {
	                    field.setAccessible(true);
	                }
	                Reference reference = field.getAnnotation(Reference.class);
	                if (reference != null) {
	                	referenceField.refer(bean, beanName, field);
	                }
	            } catch (Throwable e) {
	                logger.error("Failed to init remote service reference at filed " + field.getName() + " in class " + bean.getClass().getName() + ", cause: " + e.getMessage(), e);
	            }
	        }
	}
	
	/**
	 * reference 的属性
	 * @author wulin
	 *
	 */
	private static interface ReferenceField {
		void refer(Object bean, String beanName,Field field) throws Exception;
	}
}
