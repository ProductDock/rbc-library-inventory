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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class GetAvailableBooksCountServiceShould {

    private static final String BOOK_ID = "1";
    private static final int AVAILABLE_BOOKS_COUNT = 3;

    @InjectMocks
    private GetAvailableBooksCountService getAvailableBooksCountService;

    @Mock
    private InventoryRecordsPersistenceOutPort inventoryRecordRepository;

    @Test
    void getGetAvailableBooksCount() {
        var inventory = mock(Inventory.class);
        given(inventoryRecordRepository.findByBookId(BOOK_ID)).willReturn(Optional.of(inventory));
        given(inventory.getAvailableBooksCount()).willReturn(AVAILABLE_BOOKS_COUNT);

        assertThat(getAvailableBooksCountService.getAvailableBooksCount(BOOK_ID)).isEqualTo(AVAILABLE_BOOKS_COUNT);
    }

    @Test
    void throwInventoryException_whenBookNotExist() {
        given(inventoryRecordRepository.findByBookId(BOOK_ID)).willReturn(Optional.empty());

        assertThrows(InventoryException.class, () -> getAvailableBooksCountService.getAvailableBooksCount(BOOK_ID));
    }
}
