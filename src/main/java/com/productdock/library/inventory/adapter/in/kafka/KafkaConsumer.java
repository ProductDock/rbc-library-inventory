package com.productdock.library.inventory.adapter.in.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.productdock.library.inventory.adapter.in.kafka.messages.BookRentalStatusChanged;
import com.productdock.library.inventory.adapter.in.kafka.messages.BookRentalsMapper;
import com.productdock.library.inventory.application.port.in.UpdateBookStatusUseCase;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public record KafkaConsumer(UpdateBookStatusUseCase updateBookStatusUseCase,
                            BookRentalsMapper bookRentalsMapper,
                            ObjectMapper objectMapper) {

    @KafkaListener(topics = "${spring.kafka.topic.book-status}")
    public synchronized void listen(ConsumerRecord<String, String> message) throws JsonProcessingException {
        log.debug("Received kafka message: {}", message);

        var rentalRecordMessage = deserializeMessageFromJson(message.value());
        var rentalRecord = bookRentalsMapper.toDomain(rentalRecordMessage);
        updateBookStatusUseCase.updateBookStatus(rentalRecord);
    }

    private BookRentalStatusChanged deserializeMessageFromJson(String message) throws JsonProcessingException {
        return objectMapper.readValue(message, BookRentalStatusChanged.class);
    }
}
