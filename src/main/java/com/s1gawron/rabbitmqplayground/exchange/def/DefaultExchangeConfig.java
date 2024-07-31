package com.s1gawron.rabbitmqplayground.exchange.def;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultExchangeConfig {

    @Value("${rabbitmq.queues.default}")
    private String defaultQueueName;

    @Bean
    public Queue defaultQueue() {
        return new Queue(defaultQueueName);
    }

    public String getDefaultExchangeName() {
        return "";
    }

    public String getDefaultRoutingKey() {
        return defaultQueueName;
    }
}
