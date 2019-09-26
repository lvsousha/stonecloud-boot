package com.stone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/rocketMQ")
public class RocketMQController {

    @RequestMapping(value = "sendSimple")
    @ResponseBody
    public String sendSimple() {
      log.info("启动回避仲裁员");
      
      return "";
    }
    
    @RequestMapping(value = "sendOrder")
    @ResponseBody
    public String sendOrder() {
      log.info("启动回避仲裁员");
      
      return "";
    }
    
    @RequestMapping(value = "sendBroadcasting")
    @ResponseBody
    public String sendBroadcasting() {
      log.info("启动回避仲裁员");
      
      return "";
    }
    
    @RequestMapping(value = "sendSchedule")
    @ResponseBody
    public String sendSchedule() {
      log.info("启动回避仲裁员");
      
      return "";
    }
    
    @RequestMapping(value = "sendBatch")
    @ResponseBody
    public String sendBatch() {
      log.info("启动回避仲裁员");
      
      return "";
    }
    
    @RequestMapping(value = "sendFilter")
    @ResponseBody
    public String sendFilter() {
      log.info("启动回避仲裁员");
      
      return "";
    }
    
    @RequestMapping(value = "sendLogappender")
    @ResponseBody
    public String sendLogappender() {
      log.info("启动回避仲裁员");
      
      return "";
    }
    
    @RequestMapping(value = "sendOpenMessaging")
    @ResponseBody
    public String sendOpenMessaging() {
      log.info("启动回避仲裁员");
      
      return "";
    }
    
    @RequestMapping(value = "sendTransaction")
    @ResponseBody
    public String sendTransaction() {
      log.info("启动回避仲裁员");
      
      return "";
    }
    
}
