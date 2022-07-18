package com.productdock.library.inventory.application.service;

import com.productdock.library.inventory.application.port.out.messaging.BookAvailabilityMessagingOutPort;
import com.productdock.library.inventory.application.port.out.persistence.InventoryRecordsPersistenceOutPort;
import com.productdock.library.inventory.domain.Inventory;
import com.productdock.library.inventory.domain.exception.InventoryException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.productdock.library.inventory.data.provider.domain.BookRentalsMother.bookRentals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UpdateBookStockServiceShould {

    @InjectMocks
    private UpdateBookStockService updateBookStockService;

    @Mock
    private InventoryRecordsPersistenceOutPort inventoryRecordRepository;

    @Mock
    private BookAvailabilityMessagingOutPort bookAvailabilityMessagingOutPort;

    @Test
    void updateBookStock() throws Exception {
        var bookRentals = bookRentals();
        var inventory = mock(Inventory.class);
        given(inventoryRecordRepository.findByBookId(bookRentals.getBookId())).willReturn(Optional.of(inventory));

        updateBookStockService.updateBookStock(bookRentals);

        verify(inventory).updateStateWith(bookRentals);
        verify(inventoryRecordRepository).save(inventory);
        verify(bookAvailabilityMessagingOutPort).sendMessage(any());
    }

    @Test
    void throwInventoryException_whenBookNotExist() {
        var bookRentals = bookRentals();
        given(inventoryRecordRepository.findByBookId(bookRentals.getBookId())).willReturn(Optional.empty());

        assertThrows(InventoryException.class, () -> updateBookStockService.updateBookStock(bookRentals));
    }
}
