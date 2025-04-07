package com.javatechie.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

//    @RabbitListener(queues = "${rabbitmq.queue.name}")
//    public void consume(String message) {
//        System.out.println("Received: " + message);
//    }
}