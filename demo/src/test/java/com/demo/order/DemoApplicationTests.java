package com.demo.order;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.order.constants.RabbitConst;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testSimpleQueue() {
		String message = "Hello RabbitMQ !";
		amqpTemplate.convertAndSend(RabbitConst.SIMPLE_QUEUE_NAME, message);
		System.out.println("[x] send " + message + " ok");
	}

	@Test
	public void testWorkFairQueue() {
		for (int i = 0; i < 10; i++) {
			String message = "Hello RabbitMQ " + i;
			// 发送消息
			amqpTemplate.convertAndSend(RabbitConst.WORK_QUEUE_NAME, message);
			System.out.println(" [x] Sent '" + message + "'");
			try {
				Thread.sleep(i * 100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
    public void testFanoutQueue() {
        String message = "Hello, fanout message ";
        // 发送消息
        amqpTemplate.convertAndSend(RabbitConst.EXCHANGE_FANNOUT_NAME, "", message);
        System.out.println(" [x] Sent '" + message + "'");
    }
	
	@Test
	public void topicQueue() {
		String message = "Hello, topic message 1";
		amqpTemplate.convertAndSend(RabbitConst.EXCHANGE_TOPIC_NAME, "info", message);
		System.out.println(" [x] Sent '" + message + "'");
		
		String message2 = "Hello, topic message 2";
		amqpTemplate.convertAndSend(RabbitConst.EXCHANGE_TOPIC_NAME, "haha", message2);
		System.out.println(" [x] Sent '" + message2 + "'");
	}
}
