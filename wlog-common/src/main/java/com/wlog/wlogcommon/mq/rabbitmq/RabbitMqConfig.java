package com.wlog.wlogcommon.mq.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMqConfig {

    // 参数解释：
    // 1. 交换机名字
    // 2. 是否持久化（重启 RabbitMQ 不会丢）
    // 3. 是否自动删除（没队列绑定就删）
    @Bean
    public DirectExchange testExchange() {
        return new DirectExchange(MqConstants.TEST_EXCHANGE, true, false);
    }

    @Bean
    // 参数解释：
    // 1. 队列名
    // 2. 是否持久化
    public Queue testQueue() {
        Map<String, Object> args = new HashMap<>();
        //  指定死信交换机
        args.put("x-dead-letter-exchange", MqConstants.DLX_EXCHANGE);
        //  指定死信路由键
        args.put("x-dead-letter-routing-key", MqConstants.DLX_ROUTING_KEY);
        return new Queue(MqConstants.TEST_QUEUE, true, false, false, args);
    }

    @Bean
    public Binding testBinding() {
        return BindingBuilder
                // 把队列
                .bind(testQueue())
                // 绑定到交换机
                .to(testExchange())
                // 并且使用这个路由键
                .with(MqConstants.TEST_ROUTING_KEY);
    }

    @Bean
    public DirectExchange dlxExchange() {
        return new DirectExchange(MqConstants.DLX_EXCHANGE, true, false);
    }


    @Bean
    public Queue dlxQueue() {
        return new Queue(MqConstants.DLX_QUEUE, true);
    }


    @Bean
    public Binding dlxBinding() {
        return BindingBuilder
                .bind(dlxQueue())
                .to(dlxExchange())
                .with(MqConstants.DLX_ROUTING_KEY);
    }


}
