package com.s1gawron.rabbitmqplayground.exchange.fanout;

import com.s1gawron.rabbitmqplayground.message.dto.RabbitMQMessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutQueuesListener {

    private static final Logger log = LoggerFactory.getLogger(FanoutQueuesListener.class);

    @RabbitListener(queues = "${rabbitmq.queues.fanoutOne}")
    public void listenOnQueueOne(final RabbitMQMessageDTO message) {
        log.info("Queue 1 received message from fanout exchange: {}", message);
    }

    @RabbitListener(queues = "${rabbitmq.queues.fanoutTwo}")
    public void listenOnQueueTwo(final RabbitMQMessageDTO message) {
        log.info("Queue 2 received message from fanout exchange: {}", message);
    }

    @RabbitListener(queues = "${rabbitmq.queues.fanoutThree}")
    public void listenOnQueueThree(final RabbitMQMessageDTO message) {
        log.info("Queue 3 received message from fanout exchange: {}", message);
    }

}
