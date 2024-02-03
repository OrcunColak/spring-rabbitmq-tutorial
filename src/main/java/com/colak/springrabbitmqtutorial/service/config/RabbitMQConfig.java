package com.colak.springrabbitmqtutorial.service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    Queue queue1(@Value("${rabbitmq.queue1}") String queueName) {
        return new Queue(queueName, false);
    }

    @Bean
    DirectExchange exchange1(@Value("${rabbitmq.exchange1}") String exchange) {
        return new DirectExchange(exchange);
    }

    @Bean
    Binding binding1(Queue queue1,
                     DirectExchange exchange1,
                     @Value("${rabbitmq.routingkey1}") String routingKey) {
        return BindingBuilder.bind(queue1).to(exchange1).with(routingKey);
    }

    @Bean
    Queue queue2(@Value("${rabbitmq.queue2}") String queueName) {
        return new Queue(queueName, false);
    }

    @Bean
    DirectExchange exchange2(@Value("${rabbitmq.exchange2}") String exchange) {
        return new DirectExchange(exchange);
    }

    @Bean
    Binding binding2(Queue queue1,
                     DirectExchange exchange2,
                     @Value("${rabbitmq.routingkey2}") String routingKey) {
        return BindingBuilder.bind(queue1).to(exchange2).with(routingKey);
    }
}
