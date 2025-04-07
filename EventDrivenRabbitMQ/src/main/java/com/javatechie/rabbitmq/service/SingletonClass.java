package com.javatechie.rabbitmq.service;

public class SingletonClass
{
    private static SingletonClass instance;

    private SingletonClass() {
        // private constructor to prevent instantiation from outside
    }

    public static SingletonClass getInstance() {
        if (instance == null) {
            instance = new SingletonClass(); //Instantiation of utility class 'SingletonClass'
        }
        return instance;
    }
}
