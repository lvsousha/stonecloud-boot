package com.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.zaxxer.hikari.HikariDataSource;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration
						// @ComponentScan
public class App {
	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(App.class, args);
		DataSource dataSource = applicationContext.getBean(DataSource.class);
		System.err.println("factory:"+applicationContext.getBean(SqlSessionFactory.class).getClass().getName());
		System.err.println("datasource is :" + dataSource);
		// 检查数据库是否是hikar数据库连接池
		if (!(dataSource instanceof HikariDataSource)) {
			System.err.println(" Wrong datasource type :" + dataSource.getClass().getCanonicalName());
			System.exit(-1);
		}
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
