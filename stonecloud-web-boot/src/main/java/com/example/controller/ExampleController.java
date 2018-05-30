package com.example.controller;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.config.BasicConfigBean;
import com.example.mapper.AdminUserMapper;
import com.example.mapper.ApplicationServerMapper;
import com.example.model.AdminUser;
import com.example.model.ApplicationServer;

@RestController
public class ExampleController {

	private Logger log = LoggerFactory.getLogger(ExampleController.class);
	
	@Autowired
	private BasicConfigBean basicConfig;
	@Autowired
	private AdminUserMapper adminUserMapper;
	@Autowired
	private ApplicationServerMapper applicationServerMapper;
	
	@RequestMapping("/")
	public String home() {
		log.info(basicConfig.getName());
		AdminUser user = new AdminUser();
		user.setUserName(String.valueOf(Calendar.getInstance().getTimeInMillis()));
		ApplicationServer as = new ApplicationServer();
		as.setServerName("insert");
		applicationServerMapper.insert(as);
		user.setApplicationServer(as);
		adminUserMapper.insert(user);
		return adminUserMapper.countAdminUser().toString();
	}
	
	@RequestMapping("/adminUser/{id}")
	public AdminUser getUser(@PathVariable("id") Long id) {
		log.info(basicConfig.getName());
		AdminUser user = adminUserMapper.selectById(id);
		return user;
	}
	
	@RequestMapping("/ex")
	public AdminUser ex(@RequestBody String params) throws Exception {
		log.info(basicConfig.getName());
		Boolean flag = true;
		if(flag){
//			throw new ApiException("");
			throw new Exception("");
		}
		return null;
	}
	
}
