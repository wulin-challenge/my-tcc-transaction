package com.wulin.tcc.boot.properties;

/**
 * 恢复属性配置
 * 
 * @author wulin
 *
 */
public class RecoverProperties {

	/**
	 * 最大重试次数
	 */
	private int maxRetryCount = 30;

	/**
	 * 恢复间隔,单位 秒
	 */
	private int recoverDuration = 60; // 60 seconds

	/**
	 * 定时器执行表达式
	 */
	private String cronExpression = "0/30 * * * * ?";
	
	private int asyncTerminateThreadPoolSize = 1024;

	public int getMaxRetryCount() {
		return maxRetryCount;
	}

	public void setMaxRetryCount(int maxRetryCount) {
		this.maxRetryCount = maxRetryCount;
	}

	public int getRecoverDuration() {
		return recoverDuration;
	}

	public void setRecoverDuration(int recoverDuration) {
		this.recoverDuration = recoverDuration;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public int getAsyncTerminateThreadPoolSize() {
		return asyncTerminateThreadPoolSize;
	}

	public void setAsyncTerminateThreadPoolSize(int asyncTerminateThreadPoolSize) {
		this.asyncTerminateThreadPoolSize = asyncTerminateThreadPoolSize;
	}
	
}
