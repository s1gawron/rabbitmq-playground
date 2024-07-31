package com.s1gawron.rabbitmqplayground.exchange.direct;

import com.s1gawron.rabbitmqplayground.message.dto.RabbitMQMessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectQueueListener {

    private static final Logger log = LoggerFactory.getLogger(DirectQueueListener.class);

    @RabbitListener(queues = "${rabbitmq.queues.direct}")
    public void listen(final RabbitMQMessageDTO message) {
        log.info("Received message from direct exchange: {}", message);
    }

}
