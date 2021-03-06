package com.productdock.library.inventory.adapter.out.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.productdock.library.inventory.adapter.out.kafka.messages.BookAvailabilityChanged;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class RecordProducer {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public ProducerRecord<String, String> createKafkaRecord(String topic, BookAvailabilityChanged bookAvailabilityChanged) throws JsonProcessingException {
        log.debug("Create kafka record on topic {} with message: {}", topic, bookAvailabilityChanged);

        var serialisedMessage = serialiseMessage(bookAvailabilityChanged);
        return new ProducerRecord<>(topic, UUID.randomUUID().toString(), serialisedMessage);
    }

    private String serialiseMessage(BookAvailabilityChanged bookAvailabilityChanged) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(bookAvailabilityChanged);
    }
}
