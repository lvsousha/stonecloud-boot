package com.example.service.rocketmq;

import java.util.List;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class OrderedProducer {
  public static void main(String[] args) throws Exception {
    // Instantiate with a producer group name.
    DefaultMQProducer producer = new DefaultMQProducer("group_name_1");
    // Launch the instance.
    producer.setNamesrvAddr("192.168.20.29:9876");
    producer.setSendMsgTimeout(60000);
    producer.start();
    String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD", "TagE"};
    for (int i = 0; i < 2; i++) {
      int orderId = i % 10;
      // Create a message instance, specifying topic, tag and message body.
      Message msg = new Message("zhengchanglin", tags[i % tags.length], "KEY" + i,
          ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
      SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
        @Override
        public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
          Integer id = (Integer) arg;
          System.out.println(id + "==" + orderId + "==" + mqs.size());
          int index = id % mqs.size();
          return mqs.get(index);
        }
      }, orderId);

      System.out.printf("%s%n", sendResult);
    }
    // server shutdown
    producer.shutdown();
  }
}
