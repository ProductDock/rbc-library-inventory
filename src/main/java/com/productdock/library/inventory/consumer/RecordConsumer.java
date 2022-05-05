package com.productdock.library.inventory.consumer;

import com.productdock.library.inventory.book.BookService;
import com.productdock.library.inventory.record.RentalRecordDeserializer;
import com.productdock.library.inventory.record.RentalRecordMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public record RecordConsumer(BookService bookService, RentalRecordDeserializer rentalRecordDeserializer, RentalRecordMapper rentalRecordMapper) {

    @KafkaListener(topics = "${spring.kafka.topic.rental-record}")
    public synchronized void listen(ConsumerRecord<String, String> record) {
        var rentalRecordMessage = rentalRecordDeserializer.deserializeRentalRecord(record);
        bookService.saveBook(rentalRecordMapper.toDomain(rentalRecordMessage));
    }
}
