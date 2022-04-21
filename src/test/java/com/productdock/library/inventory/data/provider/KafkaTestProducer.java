package com.productdock.library.inventory.data.provider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.productdock.library.inventory.record.RentalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaTestProducer {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, RentalRecord rentalRecord) {
        String message = "";
        try {
            message = OBJECT_MAPPER.writeValueAsString(rentalRecord);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        kafkaTemplate.send(topic, message);
    }
}
