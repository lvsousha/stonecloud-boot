package com.example.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.stone.controller.UserController;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class TestUserController {

	private Logger log = LoggerFactory.getLogger(UserController.class);
	
	private MockMvc mvc;
	
	@Autowired
	private UserController userController;

	@Before
	public void setUp() throws Exception {
		// 初始化
		mvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	public void insertUser() throws Exception {
		JSONObject param = new JSONObject();
		param.put("key", "insertUser");
		String url = "/user/insertUser";// 访问url
		ResultActions ra = mvc.perform(
				MockMvcRequestBuilders
				.post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.content(param.toJSONString())
				.accept(MediaType.APPLICATION_JSON));
		MvcResult mvcResult = ra.andReturn();
		// 访问返回状态
		int status = mvcResult.getResponse().getStatus();
		// 接口返回结果
		String content = mvcResult.getResponse().getContentAsString();
		log.info("",status);
		log.info(content);
	}
}
