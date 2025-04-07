package com.javatechie.designpattens.application;

import com.javatechie.designpattens.factory.ShapeFactory;
import com.javatechie.designpattens.service.Shape;

public class FactoryExample {

    public static void main(String[] args) {
        ShapeFactory factory = new ShapeFactory();

        Shape shape1 = factory.getShape("circle");
        shape1.draw();

        Shape shape2 = factory.getShape("square");
        shape2.draw();
    }
}
