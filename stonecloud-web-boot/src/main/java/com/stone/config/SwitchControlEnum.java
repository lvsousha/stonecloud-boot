package com.stone.config;

import lombok.Getter;

@Getter
public enum SwitchControlEnum {

  ON("打开"),
  OFF("关闭");
  
  private String name;
  
  SwitchControlEnum(String name){
    this.name = name;
  }
  
}
