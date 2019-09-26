package com.stone.config.rocketmq;

import org.springframework.context.annotation.Configuration;
import lombok.Data;

@Data
@Configuration
public class ProducerConfig {

  private String namesrvAddr;

  private String groupName;


}
