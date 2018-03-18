package com.example.DataInit;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.alibaba.fastjson.JSON;
import com.example.mapper.ApplicationServerMapper;
import com.example.model.AdminUser;
import com.example.model.ApplicationServer;

@Component
@Order(value=1)
@PropertySource("classpath:data/data.properties")
public class InitApplicationServer implements CommandLineRunner{

	private Logger log = LoggerFactory.getLogger(InitApplicationServer.class);
	
	@Autowired
	private ApplicationServerMapper mapper;
	@Value("${data.create}")  
    private Boolean dataCreate; 
	
	@Override
	public void run(String... args) throws Exception {
		log.info("服务启动执行，执行重置数据:  "+dataCreate);
		if(dataCreate){
			mapper.deleteAll();
			File file = ResourceUtils.getFile("classpath:data/ApplicationServer.json");
			String content = FileUtils.readFileToString(file);
			List<ApplicationServer> servers = JSON.parseArray(content, ApplicationServer.class);
			for(ApplicationServer server : servers){
				for(AdminUser user : server.getAdminUsers()){
					user.setApplicationServer(server);
				}
			}
			mapper.saveAll(servers);
		}
		
	}

}
