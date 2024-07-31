package com.s1gawron.rabbitmqplayground.exchange.headers;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeadersExchangeConfig {

    private static final String HEADER_ONE_VAL = "one";

    private static final String HEADER_TWO_VAL = "two";

    @Value("${rabbitmq.exchanges.headers}")
    private String headersExchangeName;

    @Value("${rabbitmq.queues.headersOne}")
    private String headersQueueOneName;

    @Value("${rabbitmq.queues.headersTwo}")
    private String headersQueueTwoName;

    @Value("${rabbitmq.headers.defaultName}")
    private String defaultHeaderName;

    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange(headersExchangeName);
    }

    @Bean
    public Queue headersQueueOne() {
        return new Queue(headersQueueOneName);
    }

    @Bean
    public Queue headersQueueTwo() {
        return new Queue(headersQueueTwoName);
    }

    @Bean
    public Binding headersBindingOne() {
        return BindingBuilder.bind(headersQueueOne())
            .to(headersExchange())
            .where(defaultHeaderName)
            .matches(HEADER_ONE_VAL);
    }

    @Bean
    public Binding headersBindingTwo() {
        return BindingBuilder.bind(headersQueueTwo())
            .to(headersExchange())
            .where(defaultHeaderName)
            .matches(HEADER_TWO_VAL);
    }

    public String getHeadersExchangeName() {
        return headersExchangeName;
    }

    public String getDefaultHeaderName() {
        return defaultHeaderName;
    }
}
