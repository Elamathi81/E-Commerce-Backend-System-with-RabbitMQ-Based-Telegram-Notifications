package com.example.e_com_project.service;

import com.example.e_com_project.dto.OrderNotificationDto;
import com.example.e_com_project.entity.Product;
import com.example.e_com_project.repository.ProductRepo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepo productRepository;
    private final RabbitTemplate rabbitTemplate;


    private final TelegramPublisherService telegramPublisherService;

    public ProductService(ProductRepo productRepository, RabbitTemplate rabbitTemplate, TelegramPublisherService telegramPublisherService) {
        this.productRepository = productRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.telegramPublisherService = telegramPublisherService;
    }
    public Product createProduct(Product product) {
        Product savedProduct = productRepository.save(product);

        OrderNotificationDto dto = new OrderNotificationDto(
                null, // no order ID for product
                null, // no user
                product.getPrice(),
                "PRODUCT_ADDED"
        );

        rabbitTemplate.convertAndSend("telegram.exchange", "product.routing", dto); // optional
        telegramPublisherService.sendProductNotification(dto); // this is preferred

        return savedProduct;
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Product> getProductsByPriceRange(BigDecimal min, BigDecimal max) {
        return productRepository.findByPriceBetween(min, max);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }
}
