package com.stone.service.rocketmq;

import org.apache.rocketmq.client.producer.SendResult;

public interface ProducerService {

  /**
   * 发送普通消息
   * @param topic
   * @param content
   * @return
   * @throws Exception
   */
  public SendResult sendSimple(String topic, String content) throws Exception;
  
}
