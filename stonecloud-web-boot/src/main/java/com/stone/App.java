package com.stone;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
@EnableSwagger2
@MapperScan("com.stone.mapper")
@EnableTransactionManagement
@PropertySource(value = {"classpath:config/${spring.profiles.active}/flowable.yml"},
    factory = CompositePropertySourceFactory.class)
@Slf4j
public class App {
  public static void main(String[] args) {
    ApplicationContext applicationContext = SpringApplication.run(App.class, args);
    log.info("=======================");
    DataSource dataSource = applicationContext.getBean(DataSource.class);
    // System.err.println("factory:"+applicationContext.getBean(SqlSessionFactory.class).getClass().getName());
    // System.err.println("datasource is :" + dataSource);
    // 检查数据库是否是hikar数据库连接池
    try {
      Connection connection = dataSource.getConnection();
      ResultSet rs = connection.createStatement().executeQuery("SELECT 1");
      if (rs.first()) {

        System.err.println("Connection OK!");
      } else {
        System.err.println("Something is wrong");
      }
      // connection.close();
      // System.exit(0);

    } catch (SQLException e) {
      System.err.println("FAILED");
      e.printStackTrace();
      System.exit(-2);
      // TODO: handle exception
    }

  }
}
