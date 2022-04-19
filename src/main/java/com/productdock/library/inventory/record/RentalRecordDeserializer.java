package com.productdock.library.inventory.record;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.stereotype.Component;

@Component
public class RentalRecordDeserializer {

    private final ObjectMapper objectMapper;

    public RentalRecordDeserializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public RentalRecord deserializeRentalRecord(ConsumerRecord<String, String> consumerRecord) {
        try {
            return objectMapper.readValue(consumerRecord.value(), RentalRecord.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new RentalRecord();
    }
}
