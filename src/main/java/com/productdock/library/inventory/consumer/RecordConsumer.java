package com.productdock.library.inventory.consumer;

import com.productdock.library.inventory.book.InventoryRecordService;
import com.productdock.library.inventory.record.RentalRecordDeserializer;
import com.productdock.library.inventory.record.RentalRecordMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public record RecordConsumer(InventoryRecordService inventoryRecordService, RentalRecordDeserializer rentalRecordDeserializer, RentalRecordMapper rentalRecordMapper) {

    @KafkaListener(topics = "${spring.kafka.topic.book-status}")
    public synchronized void listen(ConsumerRecord<String, String> message) throws Exception {
        var rentalRecordMessage = rentalRecordDeserializer.deserializeRentalRecord(message);
        var rentalRecord = rentalRecordMapper.toDomain(rentalRecordMessage);
        inventoryRecordService.updateBookState(rentalRecord);
    }
}
