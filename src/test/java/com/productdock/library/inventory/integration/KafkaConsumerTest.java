package com.productdock.library.inventory.integration;


import com.productdock.library.inventory.adapter.out.mongo.InventoryRecordEntityRepository;
import com.productdock.library.inventory.application.port.out.messaging.BookAvailabilityMessagingOutPort;
import com.productdock.library.inventory.application.port.out.persistence.InventoryRecordsPersistenceOutPort;
import com.productdock.library.inventory.application.service.UpdateBookStatusService;
import com.productdock.library.inventory.integration.kafka.KafkaTestBase;
import com.productdock.library.inventory.integration.kafka.KafkaTestProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static com.productdock.library.inventory.data.provider.in.kafka.RentalRecordMessageMother.defaultRentalRecordMessage;
import static com.productdock.library.inventory.data.provider.out.mongo.InventoryRecordEntityMother.defaultInventoryRecordEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@SpringBootTest
class KafkaConsumerTest extends KafkaTestBase {

    @Autowired
    private KafkaTestProducer producer;

    @Autowired
    private InventoryRecordEntityRepository inventoryRecordEntityRepository;

    @Autowired
    private UpdateBookStatusService updateBookStatusService;

    @Value("${spring.kafka.topic.book-status}")
    private String topic;

    @BeforeEach
    void before() {
        inventoryRecordEntityRepository.deleteAll();
    }

    @Disabled("Flaky test when running on Sonar")
    @Test
    void shouldUpdateInventory_whenMessageReceived() throws Exception {
        givenInventoryRecordEntity();
        var rentalRecord = defaultRentalRecordMessage();

        producer.send(topic, rentalRecord);
        await()
                .atMost(Duration.ofSeconds(20))
                .until(() -> inventoryRecordEntityRepository.findByBookId("1").get().getRentedBooks() != 0);

        var entity = inventoryRecordEntityRepository.findByBookId("1");
        assertThat(entity.get().getBookCopies()).isEqualTo(3);
        assertThat(entity.get().getRentedBooks()).isEqualTo(1);
        assertThat(entity.get().getReservedBooks()).isZero();
    }

    private void givenInventoryRecordEntity() {
        inventoryRecordEntityRepository.save(defaultInventoryRecordEntity());
    }
}
