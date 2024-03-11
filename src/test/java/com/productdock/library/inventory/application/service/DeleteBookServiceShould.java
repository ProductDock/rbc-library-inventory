package com.productdock.library.inventory.application.service;

import com.productdock.library.inventory.application.port.out.persistence.InventoryRecordsPersistenceOutPort;
import com.productdock.library.inventory.domain.Inventory;
import com.productdock.library.inventory.domain.exception.InventoryException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteBookServiceShould {

    private String bookId = "1";
    @InjectMocks
    private DeleteBookService deleteBookService;
    @Mock
    private InventoryRecordsPersistenceOutPort inventoryRecordRepository;

    @Test
    void deleteBookInventory(){
        var inventory = mock(Inventory.class);
        given(inventoryRecordRepository.findByBookId(bookId)).willReturn(Optional.of(inventory));
        deleteBookService.deleteBook(bookId);

        verify(inventoryRecordRepository).deleteByBookId(bookId);
    }

    @Test
    void deleteBookInventory_WhenInventoryDoesntExist(){
        given(inventoryRecordRepository.findByBookId(bookId)).willReturn(Optional.empty());

        assertThrows(InventoryException.class, () -> deleteBookService.deleteBook(bookId));
    }

}
