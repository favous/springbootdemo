package com.demo.order.rabbit_listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.demo.order.constants.RabbitConst;

/**
 * sms消费者
 */
@RabbitListener(queues = RabbitConst.QUEUE_PS_SMS_NAME)
@Component
public class SmsRecv {

    @RabbitHandler
    public void process(String message){
        System.out.println("[sms] rev : " + message);
    }

}
