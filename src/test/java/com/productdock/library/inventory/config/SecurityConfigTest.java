package com.productdock.library.inventory.config;

import com.productdock.library.inventory.integration.kafka.KafkaTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class SecurityConfigTest extends KafkaTestBase {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenUnauthenticated_thenUnauthorizedResponse() throws Exception {
        mockMvc.perform(get("/api/inventory/book/1"))
                .andExpect(status().isUnauthorized());
    }
}
