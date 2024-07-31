package com.s1gawron.rabbitmqplayground.exchange.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicExchangeConfig {

    @Value("${rabbitmq.exchanges.topic}")
    private String topicExchangeName;

    @Value("${rabbitmq.queues.topicOne}")
    private String topicQueueOneName;

    @Value("${rabbitmq.queues.topicTwo}")
    private String topicQueueTwoName;

    @Value("${rabbitmq.queues.topicAll}")
    private String topicQueueAllName;

    @Value("${rabbitmq.routing-keys.topicOne}")
    private String topicOneRoutingKey;

    @Value("${rabbitmq.routing-keys.topicTwo}")
    private String topicTwoRoutingKey;

    @Value("${rabbitmq.routing-keys.topicAll}")
    private String topicAllRoutingKey;

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    public Queue topicOneQueue() {
        return new Queue(topicQueueOneName);
    }

    @Bean
    public Queue topicTwoQueue() {
        return new Queue(topicQueueTwoName);
    }

    @Bean
    public Queue topicAllQueue() {
        return new Queue(topicQueueAllName);
    }

    @Bean
    public Binding topicBindingQueueOne() {
        return BindingBuilder.bind(topicOneQueue())
            .to(topicExchange())
            .with(topicOneRoutingKey);
    }

    @Bean
    public Binding topicBindingQueueTwo() {
        return BindingBuilder.bind(topicTwoQueue())
            .to(topicExchange())
            .with(topicTwoRoutingKey);
    }

    @Bean
    public Binding topicBindingQueueAll() {
        return BindingBuilder.bind(topicAllQueue())
            .to(topicExchange())
            .with(topicAllRoutingKey);
    }

    public String getTopicExchangeName() {
        return topicExchangeName;
    }
}
