package com.productdock.library.inventory.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.productdock.library.inventory.record.RentalRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RecordProducer {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public ProducerRecord createKafkaRecord(String topic, RentalRecord rentalRecord) throws JsonProcessingException {
        var serialisedMessage = serialiseMessage(rentalRecord);
        var producerRecord = new ProducerRecord<>(topic, UUID.randomUUID().toString(), serialisedMessage);
        return producerRecord;
    }

    private String serialiseMessage(RentalRecord rentalRecord) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(rentalRecord);
    }
}
