package com.productdock.library.inventory.book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static com.productdock.library.inventory.data.provider.InventoryRecordEntityMother.defaultInventoryRecordEntity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class InventoryRecordApiTest {

    public static final String BOOK_ID = "1";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InventoryRecordRepository inventoryRecordRepository;

    @BeforeEach
    final void before() {
        inventoryRecordRepository.deleteAll();
    }

    @Test
    void shouldReturnAvailableBooksCount_whenRecordEntityExists() throws Exception {
        givenInventoryRecordEntity();

        mockMvc.perform(get("/api/inventory/books/" + BOOK_ID))
                .andExpect(status().isOk())
                .andExpect(content().string("3"));
    }

    @Test
    void shouldReturnBadRequest_whenRecordEntityDoesNotExist() throws Exception {
        mockMvc.perform(get("/api/inventory/books/" + BOOK_ID))
                .andExpect(status().isBadRequest());
    }

    private void givenInventoryRecordEntity() {
        inventoryRecordRepository.save(defaultInventoryRecordEntity());
    }
}
