package com.productdock.library.inventory.adapter.in.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.productdock.library.inventory.adapter.in.kafka.messages.InsertBookMessage;
import com.productdock.library.inventory.adapter.in.kafka.messages.BookRentalStatusChanged;
import com.productdock.library.inventory.adapter.in.kafka.messages.BookRentalsMapper;
import com.productdock.library.inventory.adapter.in.kafka.messages.InventoryMapper;
import com.productdock.library.inventory.application.port.in.DeleteBookUseCase;
import com.productdock.library.inventory.application.port.in.InsertBookUseCase;
import com.productdock.library.inventory.application.port.in.UpdateBookStockUseCase;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public record KafkaConsumer(UpdateBookStockUseCase updateBookStockUseCase,
                            InsertBookUseCase insertBookUseCase,
                            DeleteBookUseCase deleteBookUseCase,
                            BookRentalsMapper bookRentalsMapper,
                            InventoryMapper inventoryMapper,
                            ObjectMapper objectMapper) {

    @KafkaListener(topics = "${spring.kafka.topic.book-status}")
    public synchronized void listenBookStatus(ConsumerRecord<String, String> message) throws JsonProcessingException {
        log.debug("Received book status kafka message: {}", message);

        var bookRentalStatusChanged = deserializeBookRentalStatusChangedMessageFromJson(message.value());
        var bookRentals = bookRentalsMapper.toDomain(bookRentalStatusChanged);
        updateBookStockUseCase.updateBookStock(bookRentals);
    }

    @KafkaListener(topics = "${spring.kafka.topic.insert-book}")
    public synchronized void listenInsertInventory(ConsumerRecord<String, String> message) throws JsonProcessingException {
        log.debug("Received insert inventory kafka message: {}", message);

        var insertBookMessage = deserializeInsertBookMessageFromJson(message.value());
        var newInventory = inventoryMapper.toDomain(insertBookMessage);
        insertBookUseCase.insertBook(newInventory);
    }

    @KafkaListener(topics = "${spring.kafka.topic.delete-book}")
    public synchronized void listenDeleteInvetory(ConsumerRecord<String, String> message) throws JsonProcessingException{
        log.debug("Recieved delete book kafka message: {}", message);
        var bookId = deserializeDeleteBookMessageFromJson(message.value());
        deleteBookUseCase.deleteBook(bookId);
    }

    private BookRentalStatusChanged deserializeBookRentalStatusChangedMessageFromJson(String message) throws JsonProcessingException {
        return objectMapper.readValue(message, BookRentalStatusChanged.class);
    }

    private InsertBookMessage deserializeInsertBookMessageFromJson(String message) throws JsonProcessingException {
        return objectMapper.readValue(message, InsertBookMessage.class);
    }

    private String deserializeDeleteBookMessageFromJson(String message) throws JsonProcessingException {
        return objectMapper.readValue(message, String.class);
    }
}
