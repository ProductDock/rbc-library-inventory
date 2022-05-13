package com.productdock.library.inventory.consumer;


import com.productdock.library.inventory.book.InventoryRecordEntity;
import com.productdock.library.inventory.book.InventoryRecordRepository;
import com.productdock.library.inventory.data.provider.KafkaTestBase;
import com.productdock.library.inventory.data.provider.KafkaTestProducer;
import com.productdock.library.inventory.record.RentalRecordDeserializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static com.productdock.library.inventory.data.provider.InventoryRecordEntityMother.defaultInventoryRecordEntity;
import static com.productdock.library.inventory.data.provider.RentalRecordMessageMother.defaultRentalRecordMessage;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@SpringBootTest
class KafkaConsumerTest extends KafkaTestBase {

    @Autowired
    private KafkaTestProducer producer;

    @Autowired
    private InventoryRecordRepository inventoryRecordRepository;

    @Autowired
    private RentalRecordDeserializer rentalRecordDeserializer;

    @Value("${spring.kafka.topic.book-status}")
    private String topic;

    @BeforeEach
    final void before() {
        inventoryRecordRepository.deleteAll();
    }

    @Test
    void shouldUpdateInventory_whenMessageReceived() throws Exception {
        givenInventoryRecordEntity();
        var rentalRecord = defaultRentalRecordMessage();

        producer.send(topic, rentalRecord);
        await()
                .atMost(Duration.ofSeconds(20))
                .until(() -> inventoryRecordRepository.findByBookId("1").get().getRentedBooks() != 0);

        var entity = inventoryRecordRepository.findByBookId("1");
        assertThat(entity.get().getBookCopies()).isEqualTo(3);
        assertThat(entity.get().getRentedBooks()).isEqualTo(1);
        assertThat(entity.get().getReservedBooks()).isZero();
    }

    private void givenInventoryRecordEntity() {
        inventoryRecordRepository.save(defaultInventoryRecordEntity());
    }
}
