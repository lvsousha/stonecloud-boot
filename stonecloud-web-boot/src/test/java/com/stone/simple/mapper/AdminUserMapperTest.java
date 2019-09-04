package com.stone.simple.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.stone.App;
import com.stone.mapper.AdminUserMapper;
import com.stone.model.AdminUser;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = App.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class AdminUserMapperTest {

  @Autowired
  private AdminUserMapper mapper;      
  
  @Test
  public void test() {
      log.info("");
      mapper.insert(AdminUser.build().buildTest());
  } 
  
}
