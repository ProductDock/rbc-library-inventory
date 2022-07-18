package com.productdock.library.inventory.integration;


import com.productdock.library.inventory.adapter.out.mongo.InventoryRecordRepository;
import com.productdock.library.inventory.integration.kafka.KafkaTestBase;
import com.productdock.library.inventory.integration.kafka.KafkaTestConsumer;
import com.productdock.library.inventory.integration.kafka.KafkaTestProducer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.Duration;

import static com.productdock.library.inventory.data.provider.in.kafka.BookRentalStatusChangedMother.bookRentalStatusChanged;
import static com.productdock.library.inventory.data.provider.out.mongo.InventoryRecordEntityMother.inventoryRecordEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@SpringBootTest
class UpdateBookStockTest extends KafkaTestBase {

    @Autowired
    private KafkaTestProducer producer;

    @Autowired
    private InventoryRecordRepository inventoryRecordRepository;

    @Value("${spring.kafka.topic.book-status}")
    private String topic;

    @AfterEach
    @BeforeEach
    void before() {
        inventoryRecordRepository.deleteAll();
        KafkaTestConsumer.clear();
    }

    @Test
    void shouldUpdateBookStock_whenMessageReceived() throws Exception {
        givenInventoryRecordEntity();

        producer.send(topic, bookRentalStatusChanged());

        verifyThatStockIsUpdated();
        verifyThatAvailabilityChangedEventIsPublished();
    }

    private void verifyThatStockIsUpdated() {
        await()
                .atMost(Duration.ofSeconds(20))
                .until(() -> inventoryRecordRepository.findByBookId("1").get().getRentedBooks() != 0);

        var entity = inventoryRecordRepository.findByBookId("1");
        assertThat(entity.get().getBookCopies()).isEqualTo(3);
        assertThat(entity.get().getRentedBooks()).isEqualTo(1);
        assertThat(entity.get().getReservedBooks()).isZero();
    }

    private void verifyThatAvailabilityChangedEventIsPublished() throws IOException, ClassNotFoundException {
        await()
                .atMost(Duration.ofSeconds(20))
                .until(KafkaTestConsumer.ifFileExists());

        var bookAvailabilityChanged = KafkaTestConsumer.getMessage();

        assertThat(bookAvailabilityChanged.bookId).isEqualTo("1");
        assertThat(bookAvailabilityChanged.availableBookCount).isEqualTo(2);
    }

    private void givenInventoryRecordEntity() {
        inventoryRecordRepository.save(inventoryRecordEntity());
    }
}
