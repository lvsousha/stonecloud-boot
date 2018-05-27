package com.example.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;


@Configuration
@PropertySource("classpath:config/${spring.profiles.active}/datasource.properties")
public class DataSourceConfig {

	@Value("${spring.profiles.active}")
    private  String active;
	
	private Logger log = LoggerFactory.getLogger(DataSourceConfig.class);


    @Bean(name = "primaryDataSource")
    @Primary
    @Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.primary" )
    public DataSource primaryDataSource() {
    	log.info(active);
        log.info("数据库连接池创建中.......");
        return DataSourceBuilder.create().build();
    }
    
    @Bean(name="transactionManager") 
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(primaryDataSource());
    }
	
}
