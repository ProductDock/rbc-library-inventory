package com.productdock.library.inventory.application.service;

import com.productdock.library.inventory.application.port.out.persistence.InventoryRecordsPersistenceOutPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteBookServiceShould {

    private static final String BOOK_ID = "1";
    @InjectMocks
    private DeleteBookService deleteBookService;
    @Mock
    private InventoryRecordsPersistenceOutPort inventoryRecordRepository;

    @Test
    void deleteBookInventory() {
        deleteBookService.deleteBook(BOOK_ID);

        verify(inventoryRecordRepository).deleteByBookId(BOOK_ID);
    }
}
