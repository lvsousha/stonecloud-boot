package com.example.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.config.BasicConfigBean;
import com.example.config.DataSourceConfig;
import com.example.model.AdminUser;

@RestController
public class ExampleController {

	private Logger log = LoggerFactory.getLogger(ExampleController.class);
	
	@Autowired
	@Qualifier("entityManagerPrimary")
	private EntityManager entityManager;
	@Autowired
	private BasicConfigBean basicConfig;
	
	@RequestMapping("/")
	String home() {
		log.info("HOME");
		return basicConfig.getName();
	}
	
	@RequestMapping("/nativeQuery")
	String nativeQuery() {
		Query query = entityManager.createNativeQuery("select * from adminuser", AdminUser.class);
		@SuppressWarnings("unchecked")
		List<AdminUser> adminUsers = query.getResultList();
		System.out.println(adminUsers.get(0).getPassword());
		return "Hello World!";
	}
	
}
