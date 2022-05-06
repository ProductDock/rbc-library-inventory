package com.productdock.library.inventory.record;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

@Component
public class RentalRecordDeserializer {

    private final ObjectMapper objectMapper;

    public RentalRecordDeserializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public RentalRecordMessage deserializeRentalRecord(ConsumerRecord<String, String> consumerRecord) throws JsonProcessingException {
        return objectMapper.readValue(consumerRecord.value(), RentalRecordMessage.class);
    }
}
