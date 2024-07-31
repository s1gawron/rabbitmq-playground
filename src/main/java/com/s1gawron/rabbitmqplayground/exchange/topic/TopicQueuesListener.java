package com.s1gawron.rabbitmqplayground.exchange.topic;

import com.s1gawron.rabbitmqplayground.message.dto.RabbitMQMessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicQueuesListener {

    private static final Logger log = LoggerFactory.getLogger(TopicQueuesListener.class);

    @RabbitListener(queues = "${rabbitmq.queues.topicOne}")
    public void listenOnQueueOne(final RabbitMQMessageDTO message) {
        log.info("Queue 1 received message from topic exchange: {}", message);
    }

    @RabbitListener(queues = "${rabbitmq.queues.topicTwo}")
    public void listenOnQueueTwo(final RabbitMQMessageDTO message) {
        log.info("Queue 2 received message from topic exchange: {}", message);
    }

    @RabbitListener(queues = "${rabbitmq.queues.topicAll}")
    public void listenOnQueueAll(final RabbitMQMessageDTO message) {
        log.info("Queue * received message from topic exchange: {}", message);
    }

}
