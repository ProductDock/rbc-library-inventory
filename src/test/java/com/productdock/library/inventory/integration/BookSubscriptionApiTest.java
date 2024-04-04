package com.productdock.library.inventory.integration;

import com.productdock.library.inventory.adapter.out.mongo.BookSubscriptionRepository;
import com.productdock.library.inventory.adapter.out.mongo.InventoryRecordRepository;
import com.productdock.library.inventory.integration.kafka.KafkaTestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static com.productdock.library.inventory.data.provider.out.mongo.BookSubscriptionsEntityMother.bookSubscriptionEntity;
import static com.productdock.library.inventory.data.provider.out.mongo.InventoryRecordEntityMother.inventoryRecordEntity;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class BookSubscriptionApiTest extends KafkaTestBase {

    public static final String BOOK_ID = "1";

    public static final String USER_ID = "userEmail";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookSubscriptionRepository subscriptionsRepository;
    @Autowired
    private InventoryRecordRepository inventoryRecordRepository;

    @BeforeEach
    void before() {
        subscriptionsRepository.deleteAll();
        inventoryRecordRepository.deleteAll();
    }

    @Test
    @WithMockUser
    void shouldSubscribeUserToBook() throws Exception {
        givenUnavailableInventoryRecordEntity();
        mockMvc.perform(post("/api/inventory/subscriptions/subscribe/" + BOOK_ID)
                        .with(jwt().jwt(jwt -> {
                            jwt.claim("email", USER_ID);
                        })))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldThrowExceptionWhenNotSubscribed() throws Exception {
        mockMvc.perform(get("/api/inventory/books/" + BOOK_ID + "/subscriptions").param("userId", USER_ID)
                        .with(jwt().jwt(jwt -> {
                            jwt.claim("email", USER_ID);
                        })))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    void shouldReturnSubscriptionWhenSubscribed() throws Exception {
        givenSubscriptionEntity();

        mockMvc.perform(get("/api/inventory/books/" + BOOK_ID + "/subscriptions").param("userId", USER_ID)
                        .with(jwt().jwt(jwt -> {
                            jwt.claim("email", USER_ID);
                        })))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldThrowExceptionWhenSubscribingToUnexistingBook() throws Exception {
        mockMvc.perform(post("/api/inventory/subscriptions/subscribe/" + BOOK_ID)
                        .with(jwt().jwt(jwt -> {
                            jwt.claim("email", USER_ID);
                        })))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    void shouldThrowExceptionWhenSubscribingToAvailableBook() throws Exception {
        givenInventoryRecordEntity();
        mockMvc.perform(post("/api/inventory/subscriptions/subscribe/" + BOOK_ID)
                        .with(jwt().jwt(jwt -> {
                            jwt.claim("email", USER_ID);
                        })))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    void shouldUnsubscribeUserFromBook() throws Exception {
        givenSubscriptionEntity();

        mockMvc.perform(delete("/api/inventory/subscriptions/unsubscribe/" + BOOK_ID)
                        .with(jwt().jwt(jwt -> {
                            jwt.claim("email", USER_ID);
                        })))
                .andExpect(status().isOk());
    }

    private void givenSubscriptionEntity() {
        subscriptionsRepository.save(bookSubscriptionEntity());
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
