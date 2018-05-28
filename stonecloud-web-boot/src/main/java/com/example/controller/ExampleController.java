package com.example.controller;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.config.BasicConfigBean;
import com.example.mapper.AdminUserMapper;
import com.example.model.AdminUser;

@RestController
public class ExampleController {

	private Logger log = LoggerFactory.getLogger(ExampleController.class);
	
	@Autowired
	private BasicConfigBean basicConfig;
	@Autowired
	private AdminUserMapper adminUserMapper;
	
	@RequestMapping("/")
	public String home() {
		log.info(basicConfig.getName());
		AdminUser user = new AdminUser();
		user.setUserName(String.valueOf(Calendar.getInstance().getTimeInMillis()));
		adminUserMapper.insert(user);
		return adminUserMapper.countAdminUser().toString();
	}
	
	@RequestMapping("/adminUser/{id}")
	public AdminUser getUser(@PathVariable("id") Long id) {
		log.info(basicConfig.getName());
		AdminUser user = adminUserMapper.selectById(id);
		return user;
	}
	
}
