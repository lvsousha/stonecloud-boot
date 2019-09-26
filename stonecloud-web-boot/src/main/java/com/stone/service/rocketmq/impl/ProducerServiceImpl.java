package com.stone.service.rocketmq.impl;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.stone.service.rocketmq.ProducerService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProducerServiceImpl implements ProducerService {

  @Autowired
  private DefaultMQProducer defaultProducer;
  
  @Override
  public SendResult sendSimple(String topic, String content) throws Exception {
    Message msg = new Message(topic,
        "*" /* Tag */,
        content.getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
    );
    //Call send message to deliver message to one of brokers.
    SendResult sendResult = defaultProducer.send(msg);
    log.info(sendResult.getSendStatus().name());
    return sendResult;

    
  }

}
