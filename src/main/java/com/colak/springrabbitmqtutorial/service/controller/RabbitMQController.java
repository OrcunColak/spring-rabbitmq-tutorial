package com.colak.springrabbitmqtutorial.service.controller;

import com.colak.springrabbitmqtutorial.service.producer.RabbitMQProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/rabbitmq")
@RequiredArgsConstructor
public class RabbitMQController {

    private final RabbitMQProducer rabbitmqProducer;

    // http:localhost:8080/api/rabbitmq/sendMessage/Hello
    @GetMapping("/sendMessage/{message}")
    public ResponseEntity<String> testRabbitmq(@PathVariable String message) {
        rabbitmqProducer.sendMessage(message);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }
}
