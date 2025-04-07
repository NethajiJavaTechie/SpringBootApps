package com.javatechie.rabbitmq.controller;

import com.javatechie.rabbitmq.service.SingletonClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SingletonController  {


    private static final Logger log = LoggerFactory.getLogger(SingletonController.class);

    @GetMapping("/singleton")
    public String getSingleton() {
        SingletonClass singletonClass1 = SingletonClass.getInstance();
        SingletonClass singletonClass2 = SingletonClass.getInstance();
        log.info("This is a singleton instance 1: {}", singletonClass1.toString());
        log.info("This is a singleton instance 2: {}", singletonClass2.toString());
        return "Is Same Instance: " +singletonClass1.equals(singletonClass2);
    }

}