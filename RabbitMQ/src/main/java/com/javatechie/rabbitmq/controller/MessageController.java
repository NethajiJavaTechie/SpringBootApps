package com.javatechie.rabbitmq.controller;

import com.javatechie.rabbitmq.publisher.MessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MessageController {

    @Autowired
    private MessagePublisher publisher;

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestParam String message) {
        publisher.sendMessage(message);
        return ResponseEntity.ok("Message sent to RabbitMQ!");
    }
}