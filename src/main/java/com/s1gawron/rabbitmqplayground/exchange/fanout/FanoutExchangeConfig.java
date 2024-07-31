package com.s1gawron.rabbitmqplayground.exchange.fanout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutExchangeConfig {

    @Value("${rabbitmq.exchanges.fanout}")
    private String fanoutExchangeName;

    @Value("${rabbitmq.queues.fanoutOne}")
    private String fanoutQueueOneName;

    @Value("${rabbitmq.queues.fanoutTwo}")
    private String fanoutQueueTwoName;

    @Value("${rabbitmq.queues.fanoutThree}")
    private String fanoutQueueThreeName;

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(fanoutExchangeName);
    }

    @Bean
    public Queue fanoutQueueOne() {
        return new Queue(fanoutQueueOneName);
    }

    @Bean
    public Queue fanoutQueueTwo() {
        return new Queue(fanoutQueueTwoName);
    }

    @Bean
    public Queue fanoutQueueThree() {
        return new Queue(fanoutQueueThreeName);
    }

    @Bean
    public Binding fanoutBindingQueueOne() {
        return BindingBuilder.bind(fanoutQueueOne()).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutBindingQueueTwo() {
        return BindingBuilder.bind(fanoutQueueTwo()).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutBindingQueueThree() {
        return BindingBuilder.bind(fanoutQueueThree()).to(fanoutExchange());
    }

    public String getFanoutExchangeName() {
        return fanoutExchangeName;
    }
}
