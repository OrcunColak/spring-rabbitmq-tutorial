package com.colak.springrabbitmqtutorial.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue1}")
    String queue1;

    @Value("${rabbitmq.exchange1}")
    String exchange1;

    @Value("${rabbitmq.routingkey1}")
    String routingKey1;

    /**
     * This is another way of declaring queue + exchange + routing key
     */
    @Bean
    public Declarables topicBindings1() {
        var queue = new Queue(queue1, false);
        var exchange = new DirectExchange(exchange1);
        return new Declarables(queue, exchange, BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(routingKey1)
        );
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
