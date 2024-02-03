package com.colak.springrabbitmqtutorial.service.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    private final Queue queue1;

    private final Queue queue2;

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(queue1.getName(), message);
        log.info(" [x] Sent in queue1 '{}'", message);
    }

    public void sendFile(MultipartFile file) throws IOException {
        Message message = MessageBuilder
                .withBody(file.getBytes())
                .setHeader("ContentType", file.getContentType())
                .build();

        rabbitTemplate.convertAndSend(queue2.getName(), message);
        log.info(" [x] Sent in queue2 '{}'", message);
    }
}
