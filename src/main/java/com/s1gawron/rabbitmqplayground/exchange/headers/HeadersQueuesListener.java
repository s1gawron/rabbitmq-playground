package com.s1gawron.rabbitmqplayground.exchange.headers;

import com.s1gawron.rabbitmqplayground.message.dto.RabbitMQMessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class HeadersQueuesListener {

    private static final Logger log = LoggerFactory.getLogger(HeadersQueuesListener.class);

    @RabbitListener(queues = "${rabbitmq.queues.headersOne}")
    public void listenOnQueueOne(final RabbitMQMessageDTO message) {
        log.info("Queue 1 received message from headers exchange: {}", message);
    }

    @RabbitListener(queues = "${rabbitmq.queues.headersTwo}")
    public void listenOnQueueTwo(final RabbitMQMessageDTO message) {
        log.info("Queue 2 received message from headers exchange: {}", message);
    }

}
