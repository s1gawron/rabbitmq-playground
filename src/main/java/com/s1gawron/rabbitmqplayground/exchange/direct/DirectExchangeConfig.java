package com.s1gawron.rabbitmqplayground.exchange.direct;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectExchangeConfig {

    @Value("${rabbitmq.exchanges.direct}")
    private String directExchangeName;

    @Value("${rabbitmq.queues.direct}")
    private String directQueueName;

    @Value("${rabbitmq.routing-keys.direct}")
    private String directRoutingKey;

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(directExchangeName);
    }

    @Bean
    public Queue directQueue() {
        return new Queue(directQueueName);
    }

    @Bean
    public Binding directBinding() {
        return BindingBuilder.bind(directQueue())
            .to(directExchange())
            .with(directRoutingKey);
    }

    public String getDirectExchangeName() {
        return directExchangeName;
    }

    public String getDirectRoutingKey() {
        return directRoutingKey;
    }
}
