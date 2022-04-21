package com.productdock.library.inventory.consumer;

import com.productdock.library.inventory.book.BookService;
import com.productdock.library.inventory.record.RentalRecordDeserializer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public record RecordConsumer(BookService bookService, RentalRecordDeserializer rentalRecordDeserializer) {

    @KafkaListener(topics = "${spring.kafka.topic.rental-record-topic}")
    public synchronized void listen(ConsumerRecord<String, String> record) {
        var rentalRecord = rentalRecordDeserializer.deserializeRentalRecord(record);
        bookService.saveBook(rentalRecord);
    }
}
