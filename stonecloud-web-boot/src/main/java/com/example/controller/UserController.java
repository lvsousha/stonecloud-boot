package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mapper.AdminUserMapper;
import com.example.model.AdminUser;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="/user", tags="用户接口")
@RestController
@RequestMapping("/user")
public class UserController {

	private Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private AdminUserMapper adminUserMapper;
	
	@ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
	@RequestMapping("/login")
	public AdminUser login(@RequestBody String params) throws Exception {
		log.info("login");
		return adminUserMapper.selectById(1);
	}
	
}
