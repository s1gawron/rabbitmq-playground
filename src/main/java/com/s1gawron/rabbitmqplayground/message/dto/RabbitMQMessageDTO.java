package com.s1gawron.rabbitmqplayground.message.dto;

public record RabbitMQMessageDTO(String content,
                                 MessageExchangeType type) {

}
