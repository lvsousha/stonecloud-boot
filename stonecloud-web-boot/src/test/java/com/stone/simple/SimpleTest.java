package com.stone.simple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.stone.App;
import com.stone.config.SwitchControl;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = App.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class SimpleTest {    
    
    @Autowired
    private SwitchControl switchControl;      
    
    @Test
    public void testBasicConfigBean() {
    	log.info(switchControl.getRocketMq().toString());
    }  
    
}