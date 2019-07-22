package com.wulin.tcc.boot.properties;

/**
 * 存储类型的properties
 * @author wulin
 *
 */
public class RepositoryProperties {

	/**
	 * 存储类型: 其值有 jdbc/file/redis/zookeeper
	 */
	private String type = "jdbc";
	
	private String domain = "tcc";
	
	/**
	 * 表的后缀,默认没有后缀
	 */
	private String tbSuffix = "";

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getTbSuffix() {
		return tbSuffix;
	}

	public void setTbSuffix(String tbSuffix) {
		this.tbSuffix = tbSuffix;
	}
	
}
