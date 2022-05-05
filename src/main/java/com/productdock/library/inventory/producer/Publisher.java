package com.productdock.library.inventory.producer;

import com.productdock.library.inventory.record.RentalRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Publisher {

    private final KafkaTemplate kafkaTemplate;
    private final RecordProducer recordProducer;

    @Value("${spring.kafka.topic.book-availability}")
    private String KAFKA_TOPIC;

    public Publisher(KafkaTemplate kafkaTemplate, RecordProducer recordProducer) {
        this.kafkaTemplate = kafkaTemplate;
        this.recordProducer = recordProducer;
    }

    public void sendMessage(RentalRecord rentalRecord) {
        try {
            var kafkaRecord = recordProducer.createKafkaRecord(KAFKA_TOPIC, rentalRecord);
            var resp = kafkaTemplate.send(kafkaRecord).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
