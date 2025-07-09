package com.example.e_com_project.RabbitmqConfig;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQconfig {

    // Exchange
    public static final String EXCHANGE = "telegram.exchange";

    // Queues
    public static final String ORDER_QUEUE = "order.queue";
    public static final String PRODUCT_QUEUE = "product.queue";

    // Routing Keys
    public static final String ORDER_ROUTING_KEY = "order.routing";
    public static final String PRODUCT_ROUTING_KEY = "product.routing";

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE);
    }

    // Order Queue & Binding
    @Bean
    public Queue orderQueue() {
        return new Queue(ORDER_QUEUE, true);
    }

    @Bean
    public Binding orderBinding(Queue orderQueue, DirectExchange exchange) {
        return BindingBuilder.bind(orderQueue).to(exchange).with(ORDER_ROUTING_KEY);
    }

    // Product Queue & Binding
    @Bean
    public Queue productQueue() {
        return new Queue(PRODUCT_QUEUE, true);
    }

    @Bean
    public Binding productBinding(Queue productQueue, DirectExchange exchange) {
        return BindingBuilder.bind(productQueue).to(exchange).with(PRODUCT_ROUTING_KEY);
    }

    // Message Converter
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // Rabbit Template
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

    // Listener Container
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jsonMessageConverter());
        return factory;
    }
}
