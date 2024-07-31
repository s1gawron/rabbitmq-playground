package com.s1gawron.rabbitmqplayground.controller;

import com.s1gawron.rabbitmqplayground.controller.dto.RequestMessageDTO;
import com.s1gawron.rabbitmqplayground.exchange.def.DefaultExchangeConfig;
import com.s1gawron.rabbitmqplayground.exchange.fanout.FanoutExchangeConfig;
import com.s1gawron.rabbitmqplayground.exchange.headers.HeadersExchangeConfig;
import com.s1gawron.rabbitmqplayground.exchange.topic.TopicExchangeConfig;
import com.s1gawron.rabbitmqplayground.message.dto.MessageExchangeType;
import com.s1gawron.rabbitmqplayground.message.dto.RabbitMQMessageDTO;
import com.s1gawron.rabbitmqplayground.message.RabbitMQMessagePublisher;
import com.s1gawron.rabbitmqplayground.exchange.direct.DirectExchangeConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/message/publish")
public class MessagePublisherController {

    private final RabbitMQMessagePublisher messagePublisher;

    private final DefaultExchangeConfig defaultExchangeConfig;

    private final DirectExchangeConfig directExchangeConfig;

    private final FanoutExchangeConfig fanoutExchangeConfig;

    private final TopicExchangeConfig topicExchangeConfig;

    private final HeadersExchangeConfig headersExchangeConfig;

    public MessagePublisherController(final RabbitMQMessagePublisher messagePublisher, final DefaultExchangeConfig defaultExchangeConfig,
        final DirectExchangeConfig directExchangeConfig,
        final FanoutExchangeConfig fanoutExchangeConfig, final TopicExchangeConfig topicExchangeConfig, final HeadersExchangeConfig headersExchangeConfig) {
        this.messagePublisher = messagePublisher;
        this.defaultExchangeConfig = defaultExchangeConfig;
        this.directExchangeConfig = directExchangeConfig;
        this.fanoutExchangeConfig = fanoutExchangeConfig;
        this.topicExchangeConfig = topicExchangeConfig;
        this.headersExchangeConfig = headersExchangeConfig;
    }

    @PostMapping("/default")
    public ResponseEntity<String> publishMessageToDefaultExchange(@RequestBody final RequestMessageDTO request) {
        final RabbitMQMessageDTO message = new RabbitMQMessageDTO(request.content(), MessageExchangeType.DEFAULT);
        messagePublisher.publishMessage(message, defaultExchangeConfig.getDefaultExchangeName(), defaultExchangeConfig.getDefaultRoutingKey());

        return ResponseEntity.ok("Message published via default exchange.");
    }

    @PostMapping("/direct")
    public ResponseEntity<String> publishMessageToDirectExchange(@RequestBody final RequestMessageDTO request) {
        final RabbitMQMessageDTO message = new RabbitMQMessageDTO(request.content(), MessageExchangeType.DIRECT);
        messagePublisher.publishMessage(message, directExchangeConfig.getDirectExchangeName(), directExchangeConfig.getDirectRoutingKey());

        return ResponseEntity.ok("Message published via direct exchange.");
    }

    @PostMapping("/fanout")
    public ResponseEntity<String> publishMessageToFanoutExchange(@RequestBody final RequestMessageDTO request) {
        final RabbitMQMessageDTO message = new RabbitMQMessageDTO(request.content(), MessageExchangeType.FANOUT);
        messagePublisher.publishMessage(message, fanoutExchangeConfig.getFanoutExchangeName());

        return ResponseEntity.ok("Message published to all queues via fanout exchange.");
    }

    @PostMapping("/topic/{routingKeySuffix}")
    public ResponseEntity<String> publishMessageToTopicExchange(@RequestBody final RequestMessageDTO request, @PathVariable final String routingKeySuffix) {
        final RabbitMQMessageDTO message = new RabbitMQMessageDTO(request.content(), MessageExchangeType.TOPIC);
        final String routingKey = "topic.routing-key." + routingKeySuffix;
        messagePublisher.publishMessage(message, topicExchangeConfig.getTopicExchangeName(), routingKey);

        return ResponseEntity.ok("Message published via topic exchange.");
    }

    @PostMapping("/headers/{headerVal}")
    public ResponseEntity<String> publishMessageToHeadersExchange(@RequestBody final RequestMessageDTO request, @PathVariable final String headerVal) {
        final RabbitMQMessageDTO messageData = new RabbitMQMessageDTO(request.content(), MessageExchangeType.HEADERS);
        messagePublisher.publishMessage(messageData, headersExchangeConfig.getHeadersExchangeName(),
            Map.of(headersExchangeConfig.getDefaultHeaderName(), headerVal));

        return ResponseEntity.ok("Message published via topic exchange.");
    }

}
