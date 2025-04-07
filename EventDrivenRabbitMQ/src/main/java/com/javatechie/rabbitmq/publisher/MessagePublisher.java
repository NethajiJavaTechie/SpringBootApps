package com.javatechie.rabbitmq.publisher;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessagePublisher {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Autowired
    private AmqpTemplate template;

    public void sendMessage(String message) {
        template.convertAndSend(exchange, routingKey, message);
        System.out.println("Sent: " + message);
    }
}
