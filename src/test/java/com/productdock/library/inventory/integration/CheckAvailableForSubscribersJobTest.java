package com.productdock.library.inventory.integration;

import com.productdock.library.inventory.adapter.out.kafka.messages.BookSubscriptionMessage;
import com.productdock.library.inventory.adapter.out.mongo.BookSubscriptionRepository;
import com.productdock.library.inventory.adapter.out.mongo.InventoryRecordRepository;
import com.productdock.library.inventory.integration.kafka.KafkaTestBase;
import com.productdock.library.inventory.integration.kafka.KafkaTestConsumer;
import lombok.extern.slf4j.Slf4j;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.io.IOException;
import java.time.Duration;

import static com.productdock.library.inventory.data.provider.out.mongo.BookSubscriptionEntityMother.bookSubscriptionEntity;
import static com.productdock.library.inventory.data.provider.out.mongo.InventoryRecordEntityMother.inventoryRecordEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@SpringBootTest
@Slf4j
public class CheckAvailableForSubscribersJobTest extends KafkaTestBase {

    public static final String FILE = "testSubscription.txt";
    public static final String BOOK_ID = "1";
    public static final String USER_ID = "userEmail";

    public static MockWebServer mockCatalogBackEnd;

    @Autowired
    private BookSubscriptionRepository subscriptionRepository;
    @Autowired
    private InventoryRecordRepository inventoryRecordRepository;

    @BeforeEach
    void before() {
        subscriptionRepository.deleteAll();
        inventoryRecordRepository.deleteAll();
    }

    @BeforeAll
    static void setUp() throws IOException {
        mockCatalogBackEnd = new MockWebServer();
        mockCatalogBackEnd.start(8082);
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockCatalogBackEnd.shutdown();
    }

    @AfterAll
    static void after() {
        KafkaTestConsumer.clear(FILE);
    }

    @Test
    @WithMockUser
    void resolveSubscriptionWhenBookBecomesAvailable() throws IOException, ClassNotFoundException {
        givenUnavailableInventoryRecordEntity();
        givenSubscriptionEntity();
        givenInventoryRecordEntity();

        mockCatalogBackEnd.enqueue(new MockResponse()
                .setBody("{\"bookId\": \"1\", \"title\": \"Test Book\", \"author\": \"Author\", \"description\": \"description\"}")
                .addHeader("Content-Type", "application/json"));


        await()
                .atMost(Duration.ofSeconds(10))
                .until(() -> subscriptionRepository.findByBookIdAndUserId(BOOK_ID, USER_ID).isEmpty());

        assertThat(subscriptionRepository.findByBookIdAndUserId(BOOK_ID, USER_ID)).isEmpty();

        await()
                .atMost(Duration.ofSeconds(20))
                .until(KafkaTestConsumer.ifFileExists(FILE));

        var subscriptionMessage = (BookSubscriptionMessage) KafkaTestConsumer.getMessage(FILE);
        assertThat(subscriptionMessage.userId).isEqualTo(USER_ID);
        assertThat(subscriptionMessage.target).isEqualTo(BOOK_ID);
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
