package com.example.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:datasource.properties")
public class DataSourceConfig {

	
	private Logger log = LoggerFactory.getLogger(DataSourceConfig.class);


    @Bean(name = "primaryDataSource")
    @Primary
    @Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.primary" )
    public DataSource primaryDataSource() {
        log.info("数据库连接池创建中.......");
        return DataSourceBuilder.create().build();
    }
	
}
