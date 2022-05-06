package com.productdock.library.inventory.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.productdock.library.inventory.book.BookAvailabilityMessage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class Publisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final RecordProducer recordProducer;

    @Value("${spring.kafka.topic.book-availability}")
    private String kafkaTopic;

    public Publisher(KafkaTemplate<String, String> kafkaTemplate, RecordProducer recordProducer) {
        this.kafkaTemplate = kafkaTemplate;
        this.recordProducer = recordProducer;
    }

    public void sendMessage(BookAvailabilityMessage bookAvailabilityMessage) throws ExecutionException, InterruptedException, JsonProcessingException {
        var kafkaRecord = recordProducer.createKafkaRecord(kafkaTopic, bookAvailabilityMessage);
        kafkaTemplate.send(kafkaRecord).get();
    }
}
