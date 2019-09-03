package com.stone.service.rocketmq;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

public class OrderedConsumer {
  public static void main(String[] args) throws Exception {
    DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group_name_1");

    consumer.setNamesrvAddr("192.168.20.29:9876");
    consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

    consumer.subscribe("zhengchanglin", "*");

    consumer.registerMessageListener(new MessageListenerOrderly() {

      AtomicLong consumeTimes = new AtomicLong(0);

      @Override
      public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs,
          ConsumeOrderlyContext context) {
        System.out.println(this.consumeTimes.get());
        System.out.println(msgs.size());
        System.out.println(new String(msgs.get(0).getBody()));
        System.out.println(msgs.get(0).getQueueId());
        this.consumeTimes.incrementAndGet();
        return ConsumeOrderlyStatus.SUCCESS;
      }
    });

    consumer.start();

    System.out.printf("Consumer Started.%n");
  }
}
