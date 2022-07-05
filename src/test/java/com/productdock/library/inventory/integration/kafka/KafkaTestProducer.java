package com.productdock.library.inventory.integration.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.productdock.library.inventory.adapter.in.kafka.messages.RentalRecordMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaTestProducer {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, RentalRecordMessage rentalRecordMessage) throws JsonProcessingException {
        String message = "";
        message = OBJECT_MAPPER.writeValueAsString(rentalRecordMessage);
        kafkaTemplate.send(topic, message);
    }
}
