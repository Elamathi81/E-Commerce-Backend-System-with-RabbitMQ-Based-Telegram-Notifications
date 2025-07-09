package com.example.e_com_project.service;
import com.example.e_com_project.entity.Order;
import com.example.e_com_project.repository.OrderRepo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public String payForOrder(Long orderId) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getStatus().equals("PLACED")) {
            throw new RuntimeException("Order cannot be paid or is already paid");
        }

        order.setStatus("PAID");
        orderRepo.save(order);

        // Send payment notification via RabbitMQ
        String message = "✅ Payment received for Order #" + order.getId();
        rabbitTemplate.convertAndSend("telegram.exchange", "telegram.routing", message);

        return "Payment successful for Order #" + orderId;
    }
}
