package com.stone.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import com.stone.CompositePropertySourceFactory;

@Configuration
@PropertySource(value = {"classpath:config/${spring.profiles.active}/basicConfig.yml"},factory = CompositePropertySourceFactory.class)
@ConfigurationProperties(prefix="config.basic" )
public class BasicConfigBean {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
