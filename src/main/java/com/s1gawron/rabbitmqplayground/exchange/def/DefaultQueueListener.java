package com.s1gawron.rabbitmqplayground.exchange.def;

import com.s1gawron.rabbitmqplayground.message.dto.RabbitMQMessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DefaultQueueListener {

    private static final Logger log = LoggerFactory.getLogger(DefaultQueueListener.class);

    @RabbitListener(queues = "${rabbitmq.queues.default}")
    public void listen(final RabbitMQMessageDTO message) {
        log.info("Received message from default exchange: {}", message);
    }

}
