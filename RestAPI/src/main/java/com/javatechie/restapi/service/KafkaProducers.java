package com.javatechie.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.javatechie.restapi.utils.Constants.JAVA_TECHIE_TOPIC_NAME;

@Service
public class KafkaProducers {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public void sendMsgToTopic(String message){
        kafkaTemplate.send(JAVA_TECHIE_TOPIC_NAME, message);
    }
}
