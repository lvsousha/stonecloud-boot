package com.example.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.config.BasicConfigBean;
import com.example.model.AdminUser;

@RestController
public class ExampleController {

	@Autowired
	@Qualifier("entityManagerPrimary")
	private EntityManager entityManager;
	@Autowired
	private BasicConfigBean basicConfig;
	
	@RequestMapping("/")
	String home() {
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
