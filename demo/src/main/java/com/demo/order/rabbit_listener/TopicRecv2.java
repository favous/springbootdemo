package com.demo.order.rabbit_listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.demo.order.constants.RabbitConst;

/**
 * 消费者
 */
@RabbitListener(queues = RabbitConst.TOPIC_QUEUE_NAME2)
@Component
public class TopicRecv2 {

    @RabbitHandler
    public void process(String message){
        System.out.println("[topic2] rev : " + message);
    }

}
