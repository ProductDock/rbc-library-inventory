package com.productdock.library.inventory.integration;

import com.productdock.library.inventory.adapter.out.mongo.InventoryRecordRepository;
import com.productdock.library.inventory.integration.kafka.KafkaTestBase;
import com.productdock.library.inventory.integration.kafka.KafkaTestProducer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static com.productdock.library.inventory.data.provider.in.kafka.InsertBookMessageMother.insertBookMessage;
import static com.productdock.library.inventory.data.provider.out.mongo.InventoryRecordEntityMother.inventoryRecordEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@SpringBootTest
class InsertBookTest extends KafkaTestBase {

    @Autowired
    private KafkaTestProducer producer;

    @Autowired
    private InventoryRecordRepository inventoryRecordRepository;

    @Value("${spring.kafka.topic.insert-book}")
    private String topic;

    @AfterEach
    @BeforeEach
    void before() {
        inventoryRecordRepository.deleteAll();
    }

    @Test
    void shouldUpdateBookStock_whenMessageReceived() throws Exception {
        producer.sendInsertBook(topic, insertBookMessage());

        verifyBookStock();
    }

    @Test
    void shouldUpdateBookStock_whenMessageReceivedAndBookAlreadyExists() throws Exception {
        givenInventoryRecordEntity();

        producer.sendInsertBook(topic, insertBookMessage());

        verifyBookStock();
    }

    private void verifyBookStock() {
        await()
                .atMost(Duration.ofSeconds(20))
                .until(() -> inventoryRecordRepository.findByBookId("1").isPresent());

        var entity = inventoryRecordRepository.findByBookId("1");
        assertThat(entity.get().getBookCopies()).isEqualTo(1);
        assertThat(entity.get().getRentedBooks()).isZero();
        assertThat(entity.get().getReservedBooks()).isZero();
    }

    private void givenInventoryRecordEntity() {
        inventoryRecordRepository.save(inventoryRecordEntity());
    }
}
