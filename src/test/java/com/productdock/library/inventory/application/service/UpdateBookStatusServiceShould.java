package com.productdock.library.inventory.application.service;

import com.productdock.library.inventory.adapter.out.mongo.mapper.InventoryRecordMapper;
import com.productdock.library.inventory.application.port.out.messaging.BookAvailabilityMessagingOutPort;
import com.productdock.library.inventory.application.port.out.persistence.InventoryRecordsPersistenceOutPort;
import com.productdock.library.inventory.domain.Inventory;
import com.productdock.library.inventory.domain.RentalRecord;
import com.productdock.library.inventory.domain.exception.InventoryException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.productdock.library.inventory.data.provider.domain.RentalRecordMother.defaultRentalRecord;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UpdateBookStatusServiceShould {

    @InjectMocks
    private UpdateBookStatusService updateBookStatusService;

    @Mock
    private InventoryRecordsPersistenceOutPort inventoryRecordRepository;

    @Mock
    private BookAvailabilityMessagingOutPort bookAvailabilityMessagingOutPort;

    @Mock
    private InventoryRecordMapper inventoryRecordMapper;

    @Test
    void updateBookState() throws Exception {
        var rentalRecord = defaultRentalRecord();
        var inventory = mock(Inventory.class);
        given(inventoryRecordRepository.findByBookId(rentalRecord.getBookId())).willReturn(Optional.of(inventory));

        updateBookStatusService.updateBookStatus(rentalRecord);

        verify(inventory).updateStateWith(rentalRecord);
        verify(inventoryRecordRepository).save(inventory);
        verify(bookAvailabilityMessagingOutPort).sendMessage(any());
    }

    @Test
    void throwInventoryException_whenBookNotExist() {
        var rentalRecord = defaultRentalRecord();
        given(inventoryRecordRepository.findByBookId(rentalRecord.getBookId())).willReturn(Optional.empty());

        assertThrows(InventoryException.class, () -> updateBookStatusService.updateBookStatus(rentalRecord));
    }
}
