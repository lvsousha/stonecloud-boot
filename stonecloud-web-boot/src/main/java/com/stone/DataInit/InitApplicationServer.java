package com.stone.DataInit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value=1)
@PropertySource("classpath:data/data.properties")
public class InitApplicationServer implements CommandLineRunner{

	private Logger log = LoggerFactory.getLogger(InitApplicationServer.class);
	
	@Value("${data.create}")  
    private Boolean dataCreate; 
	
	@Override
	public void run(String... args) throws Exception {
		log.info("服务启动执行，执行重置数据:  "+dataCreate);
		if(dataCreate){
			log.info("初始化数据库");
		}
		
	}

}
