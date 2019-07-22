package com.wulin.tcc.boot.dubbo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TccBootDubboAutoConfiguration {

	@Bean
	public TccBootDubboConfigurer tccBootDubboConfigurer() {
		TccBootDubboConfigurer tccBootDubboConfigurer = new TccBootDubboConfigurer();
		return tccBootDubboConfigurer;
	}
}
