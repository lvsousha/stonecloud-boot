package com.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.config.BasicConfigBean;
import com.example.mapper.AdminUserMapper;
import com.example.model.AdminUser;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value="/example", tags="测试接口模块")
@Controller
public class ExampleController {

	private Logger log = LoggerFactory.getLogger(ExampleController.class);
	
	@Autowired
	private BasicConfigBean basicConfig;
	@Autowired
	private AdminUserMapper adminUserMapper;
	
	@RequestMapping("/")
	public ModelAndView home() {
		log.info("Home");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		return mav;
	}
	
	@RequestMapping("/login")
	public ModelAndView login() {
		log.info(basicConfig.getName());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}
	
	@ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息", httpMethod="GET")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long",paramType = "path")
	})
	@RequestMapping("/adminUser/{id}")
	@ResponseBody
	public AdminUser getUser(@PathVariable("id") Long id) {
		log.info(basicConfig.getName());
		AdminUser user = adminUserMapper.selectById(id);
		return user;
	}
	
	@RequestMapping("/ex")
	@ResponseBody
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
