package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mapper.ApplicationServerMapper;
import com.example.model.ApplicationServer;

@RestController
public class ExampleController {

	@Autowired
	private ApplicationServerMapper applicationServerMapper;
	
	@RequestMapping("/")
	String home() {
		ApplicationServer as = new ApplicationServer();
		as.setServerName("test");
		applicationServerMapper.save(as);
		return "Hello World!";
	}
	
}
