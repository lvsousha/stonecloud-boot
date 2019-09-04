package com.stone.simple.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.stone.App;
import com.stone.enums.RedisKey;
import com.stone.service.RedisService;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = App.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class RedisServiceTest {

  @Autowired
  private RedisService redisService;  
  
  @Test
  public void test() {
      log.info("");
      redisService.set(RedisKey.FAMILY, "family");
      log.info(redisService.get(RedisKey.FAMILY));
  } 
  
}
