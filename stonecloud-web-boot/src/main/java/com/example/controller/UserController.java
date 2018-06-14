package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mapper.AdminUserMapper;
import com.example.mapper.TKAdminUserMapper;
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
	@Autowired
	private TKAdminUserMapper tkAdminUserMapper;
	
	@ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
	@RequestMapping("/insertUser")
	public AdminUser insertUser(@RequestBody String params) throws Exception {
		log.info("insertUser");
		AdminUser adminUser = new AdminUser();
		adminUser.setUserName("tkMapper");
		int id = tkAdminUserMapper.insert(adminUser);
		return adminUserMapper.selectById(id);
	}
	
}
