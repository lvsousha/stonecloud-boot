package com.stone.config.druid;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.stone.config.CompositePropertySourceFactory;


@Configuration
@PropertySource(value = {"classpath:config/${spring.profiles.active}/datasource.yml"},
    factory = CompositePropertySourceFactory.class)
public class DataSourceConfig {

  @Value("${spring.profiles.active}")
  private String active;

  private Logger log = LoggerFactory.getLogger(DataSourceConfig.class);


  @Bean(name = "primaryDataSource", initMethod = "init")
  @Primary
  @ConfigurationProperties(prefix = "spring.datasource.primary")
  public DataSource primaryDataSource() {
    log.info(active);
    log.info("数据库连接池创建中.......");
    DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
    return dataSource;
  }

  @Bean(name = "sqlSessionFactory")
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(primaryDataSource());
    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/*.xml"));
    return sqlSessionFactoryBean.getObject();
  }

}
