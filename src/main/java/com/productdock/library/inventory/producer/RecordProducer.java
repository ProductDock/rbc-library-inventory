package com.productdock.library.inventory.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.productdock.library.inventory.book.BookAvailabilityMessage;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RecordProducer {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public ProducerRecord createKafkaRecord(String topic, BookAvailabilityMessage bookAvailabilityMessage) throws JsonProcessingException {
        var serialisedMessage = serialiseMessage(bookAvailabilityMessage);
        return new ProducerRecord<>(topic, UUID.randomUUID().toString(), serialisedMessage);
    }

    private String serialiseMessage(BookAvailabilityMessage bookAvailabilityMessage) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(bookAvailabilityMessage);
    }
}
