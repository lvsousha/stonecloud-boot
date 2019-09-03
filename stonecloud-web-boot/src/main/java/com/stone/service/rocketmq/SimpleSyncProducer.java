package com.stone.service.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class SimpleSyncProducer {
  public static void main(String[] args) throws Exception {
    // Instantiate with a producer group name.
    DefaultMQProducer producer = new DefaultMQProducer("group_name_1");
    try {
      // Specify name server addresses.
      producer.setNamesrvAddr("192.168.20.29:9876");
      // Launch the instance.
      producer.setSendMsgTimeout(60000);
      producer.start();
      for (int i = 0; i < 100; i++) {
        // Create a message instance, specifying topic, tag and message body.
        Message msg = new Message("zhengchanglin" /* Topic */, "TagA" /* Tag */,
            ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
        );
        // Call send message to deliver message to one of brokers.
        SendResult sendResult = producer.send(msg);
        System.out.printf("%s%n", sendResult);
      }

    } finally {
      // Shut down once the producer instance is not longer in use.
      producer.shutdown();
    }
  }
}
