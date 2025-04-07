package com.javatechie.rabbitmq.controller;

import com.javatechie.rabbitmq.publisher.MessagePublisher;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private MessagePublisher publisher;

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Autowired
    private AmqpTemplate template;

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestParam String message) {
        publisher.sendMessage(message);
        return ResponseEntity.ok("Message sent to RabbitMQ!");
    }

    @GetMapping("/receive")
    public ResponseEntity<List<String>> receive() {
        List<String> messages = new ArrayList<>();
        Object message;

        // Keep polling the queue until it's empty
        while ((message = template.receiveAndConvert(queueName)) != null) {
            messages.add(message.toString());
        }

        return ResponseEntity.ok(messages);
    }

}