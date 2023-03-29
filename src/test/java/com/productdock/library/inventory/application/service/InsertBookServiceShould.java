package com.productdock.library.inventory.application.service;

import com.productdock.library.inventory.application.port.out.persistence.InventoryRecordsPersistenceOutPort;
import com.productdock.library.inventory.domain.Inventory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class InsertBookServiceShould {

    @InjectMocks
    private InsertBookService insertBookService;

    @Mock
    private InventoryRecordsPersistenceOutPort inventoryRecordRepository;

    @Test
    void saveNewBook() {
        var bookInventory = mock(Inventory.class);

        insertBookService.insertBook(bookInventory);

        verify(inventoryRecordRepository).save(bookInventory);
    }
}
