package com.s1gawron.rabbitmqplayground.message;

import com.s1gawron.rabbitmqplayground.message.dto.RabbitMQMessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RabbitMQMessagePublisher {

    private static final Logger log = LoggerFactory.getLogger(RabbitMQMessagePublisher.class);

    private final AmqpTemplate amqpTemplate;

    private final MessageConverter jacksonConverter;

    public RabbitMQMessagePublisher(final AmqpTemplate amqpTemplate, final MessageConverter jacksonConverter) {
        this.amqpTemplate = amqpTemplate;
        this.jacksonConverter = jacksonConverter;
    }

    public void publishMessage(final RabbitMQMessageDTO message, final String exchange, final String routingKey) {
        publishMessageAndLog(message, exchange, routingKey);
    }

    public void publishMessage(final RabbitMQMessageDTO message, final String exchange) {
        publishMessageAndLog(message, exchange, null);
    }

    public void publishMessage(final RabbitMQMessageDTO messageData, final String headersExchangeName, final Map<String, Object> headers) {
        final MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeaders(headers);
        final Message message = jacksonConverter.toMessage(messageData, messageProperties);

        publishMessageAndLog(message, headersExchangeName, null);
    }

    private void publishMessageAndLog(final Object message, final String exchange, final String routingKey) {
        log.info("Publishing to {}, using routingKey {}. Message: {}", exchange, routingKey, message);
        amqpTemplate.convertAndSend(exchange, routingKey, message);
        log.info("Published to {}, using routingKey {}. Message: {}", exchange, routingKey, message);
    }
}
