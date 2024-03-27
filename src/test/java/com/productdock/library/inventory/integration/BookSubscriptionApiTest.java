package com.productdock.library.inventory.integration;

import com.productdock.library.inventory.adapter.out.mongo.BookSubscriptionsRepository;
import com.productdock.library.inventory.adapter.out.mongo.InventoryRecordRepository;
import com.productdock.library.inventory.integration.kafka.KafkaTestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static com.productdock.library.inventory.data.provider.out.mongo.BookSubscriptionsEntityMother.bookSubscriptionsEntity;
import static com.productdock.library.inventory.data.provider.out.mongo.InventoryRecordEntityMother.inventoryRecordEntity;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class BookSubscriptionApiTest extends KafkaTestBase {

    public static final String BOOK_ID = "1";

    public static final String USER_ID = "userEmail";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookSubscriptionsRepository subscriptionsRepository;
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
    void shouldThrowExceptionWhenSubscribingToUnexistingBook() throws Exception {
        mockMvc.perform(post("/api/inventory/subscriptions/subscribe/" + BOOK_ID)
                        .with(jwt().jwt(jwt -> {
                            jwt.claim("email", USER_ID);
                        })))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    void shouldReturnOkIfSubscribingToAvailableBook() throws Exception {
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
        givenSubscripotionEntity();

        mockMvc.perform(post("/api/inventory/subscriptions/unsubscribe/" + BOOK_ID)
                        .with(jwt().jwt(jwt -> {
                            jwt.claim("email", USER_ID);
                        })))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void shouldReturnTrueIfUserIsSubscribed() throws Exception {
        givenSubscripotionEntity();

        mockMvc.perform(get("/api/inventory/subscriptions/" + BOOK_ID)
                        .with(jwt().jwt(jwt -> {
                            jwt.claim("email", USER_ID);
                        })))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    @WithMockUser
    void shouldReturnFalseIfUserIsNotSubscribed() throws Exception {
        givenSubscripotionEntity();

        mockMvc.perform(get("/api/inventory/subscriptions/" + BOOK_ID)
                        .with(jwt().jwt(jwt -> {
                            jwt.claim("email", "otherUserEmail");
                        })))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    @WithMockUser
    void shouldReturnFalseIfSubscriptionsEntityDoesntExist() throws Exception {

        mockMvc.perform(get("/api/inventory/subscriptions/" + BOOK_ID)
                        .with(jwt().jwt(jwt -> {
                            jwt.claim("email", USER_ID);
                        })))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    private void givenSubscripotionEntity() {
        subscriptionsRepository.save(bookSubscriptionsEntity());
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
