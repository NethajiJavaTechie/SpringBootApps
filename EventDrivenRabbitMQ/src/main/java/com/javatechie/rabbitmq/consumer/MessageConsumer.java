package com.javatechie.rabbitmq.consumer;

import com.javatechie.rabbitmq.model.Product;
import com.javatechie.rabbitmq.publisher.MessagePublisher;
import com.javatechie.rabbitmq.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class MessageConsumer {

    private final ProductService productService;

    @Autowired
    private AmqpTemplate template;

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    public MessageConsumer(ProductService productService) {
        this.productService = productService;
    }

}