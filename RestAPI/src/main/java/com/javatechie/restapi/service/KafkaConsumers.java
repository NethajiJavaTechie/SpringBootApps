package com.javatechie.restapi.service;

import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;

import static com.javatechie.restapi.utils.Constants.JAVA_TECHIE_TOPIC_NAME;

@Service
public class KafkaConsumers {

    @KafkaListener(topics = JAVA_TECHIE_TOPIC_NAME, groupId = "test-consumer-group")
    public void listenToTopic(String receivedMessage)
    {
        System.out.println("The Message is: " + receivedMessage);
    }
}
