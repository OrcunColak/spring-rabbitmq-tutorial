package com.colak.springrabbitmqtutorial.service.producer;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.awaitility.Awaitility.await;

@SpringBootTest
@Testcontainers
@ExtendWith(OutputCaptureExtension.class)
class RabbitMQProducerIT {


    @Container
    @ServiceConnection
    public static final RabbitMQContainer rabbitMQContainer = new RabbitMQContainer("rabbitmq:3.8-management-alpine");

    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    @Test
    void testSendMessage(CapturedOutput output) {
        assertThatCode(() -> rabbitMQProducer.sendMessage("message")).doesNotThrowAnyException();
        await().atMost(10, TimeUnit.SECONDS)
                .until(isMessageConsumed(output));
    }
    private Callable<Boolean> isMessageConsumed(CapturedOutput output) {
         return () -> output.getOut().contains("Done queue 1");
    }

}
