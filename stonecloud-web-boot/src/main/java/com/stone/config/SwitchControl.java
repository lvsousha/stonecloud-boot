package com.stone.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import com.stone.CompositePropertySourceFactory;
import lombok.Data;

@Data
@Configuration
@PropertySource(value = {"classpath:config/${spring.profiles.active}/switchControl.yml"},factory = CompositePropertySourceFactory.class)
@ConfigurationProperties(prefix="switch" )
public class SwitchControl {

	private String rocketMq;
	
	private String springSecurity;
	
}
