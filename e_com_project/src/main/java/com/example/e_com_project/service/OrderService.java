package com.example.e_com_project.service;

import com.example.e_com_project.dto.OrderNotificationDto;
import com.example.e_com_project.entity.Order;
import com.example.e_com_project.entity.Order_items;
import com.example.e_com_project.entity.Product;
import com.example.e_com_project.repository.OrderItemRepo;
import com.example.e_com_project.repository.OrderRepo;
import com.example.e_com_project.repository.ProductRepo;
import com.example.e_com_project.repository.UserRepo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private OrderItemRepo orderItemRepo;

    @Autowired
    private TelegramPublisherService publisher;

    public void sendNotification(OrderNotificationDto dto) {
        rabbitTemplate.convertAndSend("telegram.exchange", "telegram.routing", dto); // ✅ correct
    }
    @Transactional
    public Order placeOrder(Map<Long, Integer> productQuantities)
    {

        Order order = new Order();

        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PLACED");

        List<Order_items> items = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (Map.Entry<Long, Integer> entry : productQuantities.entrySet()) {
            Product product = productRepo.findById(entry.getKey())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getStockQuantity() < entry.getValue()) {
                throw new RuntimeException("Insufficient stock for product: " + product.getName());
            }

            product.setStockQuantity(product.getStockQuantity() - entry.getValue());
            productRepo.save(product);

            Order_items item = new Order_items();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(entry.getValue());
            items.add(item);

            total = total.add(product.getPrice().multiply(BigDecimal.valueOf(entry.getValue())));
        }

        order.setTotalAmount(total);
        order.setOrderItems(items);

        order = orderRepo.save(order);

        OrderNotificationDto dto = new OrderNotificationDto(
                order.getId(),
                "",  // or email if available
                total,
                order.getStatus()
        );

        rabbitTemplate.convertAndSend("telegram.exchange", "telegram.routing", dto);

        publisher.sendOrderNotification(dto);


        return order;
    }
}
