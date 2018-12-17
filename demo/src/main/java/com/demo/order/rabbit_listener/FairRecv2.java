package com.demo.order.rabbit_listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.demo.order.constants.RabbitConst;

/**
 * 消费者
 */
@RabbitListener(queues = RabbitConst.WORK_QUEUE_NAME)
@Component
public class FairRecv2 {

    @RabbitHandler
    public void process(String message){
    	System.out.println("[2] rev : " + message);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
