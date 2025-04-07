package com.javatechie.restapi.service;

import com.javatechie.restapi.model.Product;

import java.util.List;

public interface ProductService {

    public List<Product> getAllProducts();

    public Product addProduct(Product product);
}
