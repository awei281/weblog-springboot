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

    @Bean
    public DirectExchange testExchange() {
        return new DirectExchange(MqConstants.TEST_EXCHANGE, true, false);
    }

    @Bean

    public Queue testQueue() {
        //  指定死信交换机 指定死信路由键
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", MqConstants.DLX_EXCHANGE);
        args.put("x-dead-letter-routing-key", MqConstants.DLX_ROUTING_KEY);
        return new Queue(MqConstants.TEST_QUEUE, true, false, false, args);
    }

    @Bean
    public Binding testBinding() {
        return BindingBuilder
                .bind(testQueue())
                .to(testExchange())
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


    @Bean
    public DirectExchange retryExchange() {
        return new DirectExchange(MqConstants.RETRY_EXCHANGE, true, false);
    }

    @Bean
    public Queue retryQueue() {
        Map<String, Object> args = new HashMap<>();
        // 【修正建议 1】确保注释与代码一致，这里设定为 10 秒
        args.put("x-message-ttl", 10_000);
        // 指定死信（过期后）发送去的交换机（即原本的业务交换机）
        args.put("x-dead-letter-exchange", MqConstants.TEST_EXCHANGE);
        // 指定死信的路由键（原本业务的 RoutingKey）
        // 这样 10s 后消息会自动回到业务队列被重新消费
        args.put("x-dead-letter-routing-key", MqConstants.TEST_ROUTING_KEY);
        return new Queue(MqConstants.RETRY_QUEUE, true, false, false, args);
    }

    @Bean
    public Queue retry30sQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 30_000);
        args.put("x-dead-letter-exchange", MqConstants.TEST_EXCHANGE);
        args.put("x-dead-letter-routing-key", MqConstants.TEST_ROUTING_KEY);
        return new Queue(MqConstants.RETRY_QUEUE_30S, true, false, false, args);
    }
    @Bean
    public Queue retry120sQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 120_000);
        args.put("x-dead-letter-exchange", MqConstants.TEST_EXCHANGE);
        args.put("x-dead-letter-routing-key", MqConstants.TEST_ROUTING_KEY);
        return new Queue(MqConstants.RETRY_QUEUE_120S, true, false, false, args);
    }


    // 确保 Binding 中的 Bean 调用方式正确
    @Bean
    public Binding retryBinding() {
        return BindingBuilder
                // 这里直接调用方法是基于 Spring @Configuration 的代理机制，是正确的
                .bind(retryQueue())
                .to(retryExchange())
                .with(MqConstants.RETRY_ROUTING_KEY);
    }

    @Bean
    public Binding retry30Binding() {
        return BindingBuilder
                // 这里直接调用方法是基于 Spring @Configuration 的代理机制，是正确的
                .bind(retry30sQueue())
                .to(retryExchange())
                .with(MqConstants.RETRY_ROUTING_KEY_30S);
    }


    @Bean
    public Binding retry120Binding() {
        return BindingBuilder
                // 这里直接调用方法是基于 Spring @Configuration 的代理机制，是正确的
                .bind(retry120sQueue())
                .to(retryExchange())
                .with(MqConstants.RETRY_ROUTING_KEY_120S);
    }


}
