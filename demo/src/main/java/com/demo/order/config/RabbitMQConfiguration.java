package com.demo.order.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.order.constants.RabbitConst;

@Configuration
public class RabbitMQConfiguration {
	
    // 简单队列
	@Bean
    public Queue queue(){
        return new Queue(RabbitConst.SIMPLE_QUEUE_NAME, false, false, false, null);
    }

	// 工作队列（公平，轮循）
    @Bean
    public Queue workQueue(){
        return new Queue(RabbitConst.WORK_QUEUE_NAME, false, false, false, null);
    }

    // 订阅模式*******************
    @Bean("fanoutExchange")
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(RabbitConst.EXCHANGE_FANNOUT_NAME);
    }
    @Bean
    public Queue fanoutSmsQueue(){
    	return new Queue(RabbitConst.QUEUE_PS_SMS_NAME, false, false, false, null);
    }
    @Bean
    public Queue fanoutEmailQueue(){
    	return new Queue(RabbitConst.QUEUE_PS_EMAIL_NAME, false, false, false, null);
    }
    // 绑定
    @Bean
    public Binding smsQueueExchangeBinding(FanoutExchange fanoutExchange, Queue fanoutSmsQueue){
    	return BindingBuilder.bind(fanoutSmsQueue).to(fanoutExchange);
    }
    @Bean
    public Binding emailQueueExchangeBinding(FanoutExchange fanoutExchange, Queue fanoutEmailQueue){
    	return BindingBuilder.bind(fanoutEmailQueue).to(fanoutExchange);
    }
    
    // 路由与主题模式********************
    @Bean("topicExchange")
    public TopicExchange topicExchange(){
    	return new TopicExchange(RabbitConst.EXCHANGE_TOPIC_NAME);
    }
    @Bean
    public Queue topicQueue(){
    	return new Queue(RabbitConst.TOPIC_QUEUE_NAME, false, false, false, null);
    }
    @Bean
    public Queue topicQueue2(){
    	return new Queue(RabbitConst.TOPIC_QUEUE_NAME2, false, false, false, null);
    }
    @Bean
    public Binding topicQueueExchangeBinding(Queue topicQueue, TopicExchange topicExchange){
        return BindingBuilder.bind(topicQueue).to(topicExchange).with("info");
    }
    @Bean
    public Binding topicQueue2ExchangeBinding(Queue topicQueue2, TopicExchange topicExchange){
    	return BindingBuilder.bind(topicQueue2).to(topicExchange).with("haha");
    }

}
