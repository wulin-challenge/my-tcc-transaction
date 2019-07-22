package com.wulin.tcc.boot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * tcc的配置项
 * @author wubo
 *
 */
@Configuration
@ConfigurationProperties(prefix="tcc",ignoreUnknownFields=true)
public class TccProperties {

	private RecoverProperties recover = new RecoverProperties();
	
	private RepositoryProperties repository = new RepositoryProperties();

	public RecoverProperties getRecover() {
		return recover;
	}

	public void setRecover(RecoverProperties recover) {
		this.recover = recover;
	}

	public RepositoryProperties getRepository() {
		return repository;
	}

	public void setRepository(RepositoryProperties repository) {
		this.repository = repository;
	}
}
