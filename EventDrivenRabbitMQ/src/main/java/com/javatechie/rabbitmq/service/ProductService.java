package com.javatechie.rabbitmq.service;

import com.javatechie.rabbitmq.model.Product;

import java.util.List;

public interface ProductService {

    public List<Product> getAllProducts();

    public Product addProduct(Product product);
}
