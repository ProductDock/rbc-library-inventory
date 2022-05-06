package com.productdock.library.inventory.consumer;


import com.productdock.library.inventory.book.InventoryRecordRepository;
import com.productdock.library.inventory.data.provider.KafkaTestBase;
import com.productdock.library.inventory.data.provider.KafkaTestProducer;
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

    @Value("${spring.kafka.topic.book-status}")
    private String topic;

    @BeforeEach
    final void before() {
        inventoryRecordRepository.deleteAll();
        inventoryRecordRepository.save(defaultInventoryRecordEntity());
    }

    @Test
    void shouldUpdateInventory_whenMessageReceived() throws Exception{
        var rentalRecord = defaultRentalRecordMessage();

        producer.send(topic, rentalRecord);
        await()
                .atMost(Duration.ofSeconds(5))
                .until(() -> inventoryRecordRepository.findById("1").isPresent());

        var entity = inventoryRecordRepository.findById("1");
        assertThat(entity.get().getBookCopies()).isEqualTo(3);
        assertThat(entity.get().getRentedBooks()).isEqualTo(1);
        assertThat(entity.get().getReservedBooks()).isZero();
    }
}
