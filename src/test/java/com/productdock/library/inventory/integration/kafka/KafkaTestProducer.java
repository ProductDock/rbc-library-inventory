package com.productdock.library.inventory.integration.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.productdock.library.inventory.adapter.in.kafka.messages.BookRentalStatusChanged;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaTestProducer {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, BookRentalStatusChanged bookRentalStatusChanged) throws JsonProcessingException {
        String message = OBJECT_MAPPER.writeValueAsString(bookRentalStatusChanged);
        kafkaTemplate.send(topic, message);
    }
}
