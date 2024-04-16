package com.productdock.library.inventory.integration;

import com.productdock.library.inventory.adapter.out.mongo.BookSubscriptionRepository;
import com.productdock.library.inventory.adapter.out.mongo.InventoryRecordRepository;
import com.productdock.library.inventory.integration.kafka.KafkaTestBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.Duration;

import static com.productdock.library.inventory.data.provider.out.mongo.BookSubscriptionEntityMother.bookSubscriptionEntity;
import static com.productdock.library.inventory.data.provider.out.mongo.InventoryRecordEntityMother.inventoryRecordEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@SpringBootTest
@EnableScheduling
@Slf4j
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:0", "port=0"})
public class CheckAvailableForSubscribersJobTest extends KafkaTestBase {

    public static final String BOOK_ID = "1";
    public static final String USER_ID = "userEmail";

    @Autowired
    private BookSubscriptionRepository subscriptionRepository;
    @Autowired
    private InventoryRecordRepository inventoryRecordRepository;

    @BeforeEach
    @AfterEach
    void before() {
        subscriptionRepository.deleteAll();
        inventoryRecordRepository.deleteAll();
    }

    @Test
    @WithMockUser
    void resolveSubscriptionWhenBookBecomesAvailable() {
        givenUnavailableInventoryRecordEntity();
        givenSubscriptionEntity();
        givenInventoryRecordEntity();

        await()
                .atMost(Duration.ofSeconds(10))
                .until(() -> subscriptionRepository.findByBookIdAndUserId(BOOK_ID, USER_ID).isEmpty());

        assertThat(subscriptionRepository.findByBookIdAndUserId(BOOK_ID, USER_ID)).isEmpty();
    }

    @Test
    @WithMockUser
    void shouldNotResolveSubscriptionsIfBookUnavailable() {
        givenUnavailableInventoryRecordEntity();
        givenSubscriptionEntity();

        await()
                .atMost(Duration.ofSeconds(10))
                .until(() -> subscriptionRepository.findByBookIdAndUserId(BOOK_ID, USER_ID).isPresent());

        assertThat(subscriptionRepository.findByBookIdAndUserId(BOOK_ID, USER_ID)).isNotEmpty();
    }

    private void givenSubscriptionEntity() {
        subscriptionRepository.save(bookSubscriptionEntity());
    }

    private void givenInventoryRecordEntity() {
        inventoryRecordRepository.save(inventoryRecordEntity());
    }

    private void givenUnavailableInventoryRecordEntity() {
        var entity = inventoryRecordEntity();
        entity.setRentedBooks(3);
        inventoryRecordRepository.save(entity);
    }

}
