package com.stone.config.rocketmq;

import java.io.UnsupportedEncodingException;
import java.util.List;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomConsumer implements ApplicationListener<ContextRefreshedEvent>{

  
  @Autowired
  private ConsumerConfig consumerConfig;
  
  public void onApplicationEvent(ContextRefreshedEvent arg0) {
    try {
        listener("t_TopicTest", "Tag1");
    } catch (MQClientException e) {
        log.error("消费者监听器启动失败", e);
    }
    
}

  // 开启消费者监听服务
  public void listener(String topic, String tag) throws MQClientException {
      log.info("开启" + topic + ":" + tag + "消费者-------------------");
      log.info(consumerConfig.toString());

      DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerConfig.getGroupName());

      consumer.setNamesrvAddr(consumerConfig.getNamesrvAddr());

      consumer.subscribe(topic, tag);

      // 开启内部类实现监听
      consumer.registerMessageListener(new MessageListenerConcurrently() {
          @Override
          public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
              return dealBody(msgs);
          }
      });

      consumer.start();

      log.info("rocketmq启动成功---------------------------------------");

  }
  
  public ConsumeConcurrentlyStatus dealBody(List<MessageExt> msgs)  {
    int num = 1;
    log.info("进入");
    for(MessageExt msg : msgs) {
        log.info("第" + num + "次消息");
        try {
            String msgStr = new String(msg.getBody(), "utf-8");
            log.info(msgStr);
        } catch (UnsupportedEncodingException e) {
            log.error("body转字符串解析失败");
        }
    }
    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
}
}
