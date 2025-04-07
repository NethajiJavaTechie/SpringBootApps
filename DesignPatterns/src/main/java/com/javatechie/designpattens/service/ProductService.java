package com.javatechie.designpattens.service;

import com.javatechie.designpattens.model.Product;

import java.util.List;

public interface ProductService {

    public List<Product> getAllProducts();

    public Product addProduct(Product product);
}
