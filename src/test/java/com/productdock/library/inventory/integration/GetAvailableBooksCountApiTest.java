package com.productdock.library.inventory.integration;

import com.productdock.library.inventory.adapter.out.mongo.InventoryRecordRepository;
import com.productdock.library.inventory.integration.kafka.KafkaTestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static com.productdock.library.inventory.data.provider.out.mongo.InventoryRecordEntityMother.inventoryRecordEntity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class GetAvailableBooksCountApiTest extends KafkaTestBase {

    public static final String BOOK_ID = "1";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InventoryRecordRepository inventoryRecordRepository;

    @BeforeEach
    void before() {
        inventoryRecordRepository.deleteAll();
    }

    @Test
    @WithMockUser
    void shouldReturnAvailableBooksCount_whenRecordEntityExists() throws Exception {
        givenInventoryRecordEntity();

        mockMvc.perform(get("/api/inventory/book/" + BOOK_ID))
                .andExpect(status().isOk())
                .andExpect(content().string("3"));
    }

    @Test
    @WithMockUser
    void shouldReturnBadRequest_whenRecordEntityDoesNotExist() throws Exception {
        mockMvc.perform(get("/api/inventory/book/" + BOOK_ID))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnNotAuthorized_whenUserIsUnauthenticated() throws Exception {
        mockMvc.perform(get("/api/inventory/book/" + BOOK_ID))
                .andExpect(status().isUnauthorized());
    }

    private void givenInventoryRecordEntity() {
        inventoryRecordRepository.save(inventoryRecordEntity());
    }
}
