package com.kafka.demo.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

public class MessagePublisher {
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        String topicName = "my-first-topic";
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);

        future.whenComplete((result, exception) -> {
            if (exception == null) {
                System.out.println("Sent message=[" + message + "] " + "with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send the message=[" + message + "] due to: " + exception.getMessage());
            }
        });
    }
}
