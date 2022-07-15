package com.productdock.library.inventory.adapter.out.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.productdock.library.inventory.adapter.out.kafka.messages.BookAvailabilityChanged;
import com.productdock.library.inventory.application.port.out.messaging.BookAvailabilityMessagingOutPort;
import com.productdock.library.inventory.domain.Inventory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
@Slf4j
public class KafkaPublisher implements BookAvailabilityMessagingOutPort {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final RecordProducer recordProducer;

    @Value("${spring.kafka.topic.book-availability}")
    private String kafkaTopic;

    public KafkaPublisher(KafkaTemplate<String, String> kafkaTemplate, RecordProducer recordProducer) {
        this.kafkaTemplate = kafkaTemplate;
        this.recordProducer = recordProducer;
    }

    public void sendMessage(Inventory inventory) throws ExecutionException, InterruptedException, JsonProcessingException {
        var bookAvailabilityMessage = new BookAvailabilityChanged(inventory.getBookId(), inventory.getAvailableBooksCount());
        log.debug("Sent kafka message: {} on kafka topic: {}", bookAvailabilityMessage, kafkaTopic);

        var kafkaRecord = recordProducer.createKafkaRecord(kafkaTopic, bookAvailabilityMessage);
        kafkaTemplate.send(kafkaRecord).get();
    }
}
