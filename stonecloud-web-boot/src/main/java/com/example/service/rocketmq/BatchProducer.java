package com.example.service.rocketmq;

import java.util.ArrayList;
import java.util.List;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

public class BatchProducer {
  public static void main(String[] args) throws Exception {
    // Instantiate with a producer group name.
    DefaultMQProducer producer = new DefaultMQProducer("group_name_1");
    try {
      // Specify name server addresses.
      producer.setNamesrvAddr("192.168.20.29:9876");
      // Launch the instance.
      producer.setSendMsgTimeout(60000);
      producer.start();
      String topic = "zhengchanglin";
      List<Message> messages = new ArrayList<>();
      messages.add(new Message(topic, "TagA", "OrderID001", "Hello world 0".getBytes()));
      messages.add(new Message(topic, "TagA", "OrderID002", "Hello world 1".getBytes()));
      messages.add(new Message(topic, "TagA", "OrderID003", "Hello world 2".getBytes()));
      producer.send(messages);

    } finally {
      // Shut down once the producer instance is not longer in use.
      producer.shutdown();
    }
  }
}
