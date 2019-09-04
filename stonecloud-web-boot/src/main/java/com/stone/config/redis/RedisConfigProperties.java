//package com.stone.config.redis;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.stereotype.Component;
//import com.stone.CompositePropertySourceFactory;
//import lombok.Data;
//
//@Component
//@PropertySource(value = {"classpath:config/${spring.profiles.active}/redis.yml"},factory = CompositePropertySourceFactory.class)
//@ConfigurationProperties(prefix = "spring.redis")
//@Data
//public class RedisConfigProperties {
//
//  private Integer database;
//  private String host;
//  private String port;
//  private String password;
//  private String timeout;
//}
