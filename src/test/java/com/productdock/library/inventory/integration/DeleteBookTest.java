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

import java.time.Duration;

import static com.productdock.library.inventory.data.provider.out.mongo.InventoryRecordEntityMother.inventoryRecordEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@SpringBootTest
public class DeleteBookTest extends KafkaTestBase {

    public static final String BOOK_ID = "1";
    @Autowired
    private KafkaTestProducer producer;
    @Autowired
    private InventoryRecordRepository inventoryRecordRepository;
    @Value("${spring.kafka.topic.delete-book}")
    private String topic;

    @AfterEach
    @BeforeEach
    void before() {
        inventoryRecordRepository.deleteAll();
        KafkaTestConsumer.clear();
    }

    @Test
    void shouldDeleteBookStock_whenMessageReceived() throws Exception {
        givenInventoryRecordEntity();
        producer.sendDeleteBook(topic, BOOK_ID);
        await()
                .atMost(Duration.ofSeconds(20))
                .until(() -> inventoryRecordRepository.findByBookId(BOOK_ID).isEmpty());

        assertThat(inventoryRecordRepository.findByBookId(BOOK_ID)).isEmpty();
    }

    private void givenInventoryRecordEntity() {
        inventoryRecordRepository.save(inventoryRecordEntity());
    }
}
