package com.javatechie.designpattens.factory;

import com.javatechie.designpattens.service.CircleShape;
import com.javatechie.designpattens.service.Shape;
import com.javatechie.designpattens.service.SquareShape;

public class ShapeFactory {
    public Shape getShape(String type) {
        if (type.equalsIgnoreCase("circle")) return new CircleShape();
        else if (type.equalsIgnoreCase("square")) return new SquareShape();
        return null;
    }
}