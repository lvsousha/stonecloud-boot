package com.stone.config.rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import com.stone.CompositePropertySourceFactory;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Configuration
@PropertySource(
    value = {"classpath:config/${spring.profiles.active}/rocketMQ.yml",
        "classpath:config/${spring.profiles.active}/switchControl.yml"},
    factory = CompositePropertySourceFactory.class)
public class RocketMQConfig {

  @Bean
  @ConfigurationProperties(prefix = "rocketmq.consumer")
  public ConsumerConfig consumerConfig() {
    ConsumerConfig config = new ConsumerConfig();
    return config;
  }
  @Bean
  @ConfigurationProperties(prefix = "rocketmq.producer")
  public ProducerConfig producerConfig() {
    ProducerConfig config = new ProducerConfig();
    return config;
  }

  @Bean
  @ConditionalOnProperty(name = "switch.rocketMq", havingValue = "true")
  public DefaultMQProducer defaultProducer() throws MQClientException {
    ProducerConfig producerConfigure = producerConfig();
    log.info(producerConfigure.toString());
    log.info("defaultProducer 正在创建---------------------------------------");
    DefaultMQProducer producer = new DefaultMQProducer(producerConfigure.getGroupName());
    producer.setNamesrvAddr(producerConfigure.getNamesrvAddr());
    producer.setVipChannelEnabled(false);
    producer.setRetryTimesWhenSendAsyncFailed(10);
    producer.start();
    log.info("rocketmq producer server开启成功---------------------------------.");
    return producer;
  }


}
