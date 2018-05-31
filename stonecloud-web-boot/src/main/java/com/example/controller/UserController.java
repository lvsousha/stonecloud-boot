package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mapper.AdminUserMapper;
import com.example.model.AdminUser;

@RestController
@RequestMapping("/user")
public class UserController {

	private Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private AdminUserMapper adminUserMapper;
	
	@RequestMapping("/login")
	public AdminUser login(@RequestBody String params) throws Exception {
		log.info("login");
		return adminUserMapper.selectById(1);
	}
	
}
