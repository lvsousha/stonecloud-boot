package com.stone.config.flowable;

import org.flowable.spring.SpringProcessEngineConfiguration;
import org.flowable.spring.boot.EngineConfigurationConfigurer;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class FlowableConfig implements EngineConfigurationConfigurer<SpringProcessEngineConfiguration> {

  @Override
  public void configure(SpringProcessEngineConfiguration engineConfiguration) {
    // TODO Auto-generated method stub
    engineConfiguration.setActivityFontName("宋体");
    engineConfiguration.setLabelFontName("宋体");
    engineConfiguration.setAnnotationFontName("宋体");

  }

}
