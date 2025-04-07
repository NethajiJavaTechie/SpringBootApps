package com.javatechie.restapi.controller;

import com.javatechie.restapi.model.Product;
import com.javatechie.restapi.service.KafkaProducers;
import com.javatechie.restapi.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    //private final KafkaProducers kafkaProducers;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct()
    {
        Product product = new Product();
        product.setName("Organic Bananas");
        product.setPrice(100L);
        product.setQuantity(50L);
        product = productService.addProduct(product);
        if(product!=null){
            String message = "Product added: "+product;
            //kafkaProducers.sendMsgToTopic(message);
            log.info(message);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("findProducts")
    public ResponseEntity<List<Product>> getAllProducts()
    {
        List<Product> products = new ArrayList<>();
        try
        {
             products = productService.getAllProducts();
            if(products.isEmpty())
            {
                log.info("No records found");
            } else {
                log.info("{} Records found", products.size());
                return new ResponseEntity<>(products, HttpStatus.OK);
            }
        } catch(Exception e)
        {
            log.info("Exception: {}", e.getMessage());
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
