package com.colak.springrabbitmqtutorial.service.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQConsumer {

    @RabbitListener(queues = "queue1")
    public void receive(String in) throws InterruptedException {
        log.info(" [x] Received  in queue 1'{}", in);
        Thread.sleep(3000);
        log.info("Done queue 1");
    }

    @RabbitListener(queues = "queue2")
    public void receiveFile(Message message) {
        byte[] fileBytes = message.getBody();
        String contentType = (String) message.getMessageProperties().getHeaders().get("ContentType");

        // Process the received file bytes and contentType as needed
        // You can save the file, perform further processing, etc.

        log.info("Received file with content type: " + contentType);
    }
}
