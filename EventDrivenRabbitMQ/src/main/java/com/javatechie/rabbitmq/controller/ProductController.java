package com.javatechie.rabbitmq.controller;

import com.javatechie.rabbitmq.model.Product;
import com.javatechie.rabbitmq.publisher.MessagePublisher;
import com.javatechie.rabbitmq.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
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

    private final MessagePublisher messagePublisher;

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    public ProductController(ProductService productService, MessagePublisher messagePublisher, RabbitTemplate rabbitTemplate) {
        this.productService = productService;
        this.messagePublisher = messagePublisher;
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product)
    {
        //product = productService.addProduct(product);
        if(product!=null){
            messagePublisher.sendProductDetails(product);
            log.info("Product queued: {}", product);
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

    @GetMapping("/readRabbitMq")
    public ResponseEntity<List<Product>> readRabbitMq() {
        List<Product> productList = new ArrayList<>();
        Product product;
        // Keep polling the queue until it's empty
        while ((product = (Product) rabbitTemplate.receiveAndConvert(queueName)) != null) {
            log.info("Product in Queue: {}", product);
            productList.add(product);
        }

        return ResponseEntity.ok(productList);
    }

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void consumeProducts(Product product) {
        List<Product> productList = new ArrayList<>();
        log.info("Product found in queue: {}", product);
        try
        {
            productService.addProduct(product);
            log.info("Product inserted into database: {}", product);
        }
        catch (Exception e)
        {
            log.error("Error in inserting product {}", e.getMessage());
        }
    }
}
