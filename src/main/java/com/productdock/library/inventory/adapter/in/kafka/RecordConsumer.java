package com.productdock.library.inventory.adapter.in.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.productdock.library.inventory.book.InventoryRecordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public record RecordConsumer(InventoryRecordService inventoryRecordService,
                             RentalRecordDeserializer rentalRecordDeserializer, RentalRecordMapper rentalRecordMapper) {

    @KafkaListener(topics = "${spring.kafka.topic.book-status}")
    public synchronized void listen(ConsumerRecord<String, String> message) throws JsonProcessingException {
        log.debug("Received kafka message: {}", message);

        var rentalRecordMessage = rentalRecordDeserializer.deserializeRentalRecord(message);
        var rentalRecord = rentalRecordMapper.toDomain(rentalRecordMessage);
        inventoryRecordService.updateBookState(rentalRecord);
    }
}