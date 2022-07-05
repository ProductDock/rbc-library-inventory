package com.productdock.library.inventory.application.service;

import com.productdock.library.inventory.adapter.out.mongo.mapper.InventoryRecordMapper;
import com.productdock.library.inventory.application.port.out.messaging.BookAvailabilityMessagingOutPort;
import com.productdock.library.inventory.application.port.out.persistence.InventoryRecordsPersistenceOutPort;
import com.productdock.library.inventory.domain.Inventory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.productdock.library.inventory.data.provider.domain.RentalRecordMother.defaultRentalRecord;
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
    void updateBookState_whenBookExists() throws Exception {
        var rentalRecord = defaultRentalRecord();
        var inventory = mock(Inventory.class);
        given(inventoryRecordRepository.getInventoryFrom(rentalRecord.getBookId())).willReturn(inventory);

        updateBookStatusService.updateBookState(rentalRecord);

        verify(inventory).updateStateWith(rentalRecord);
        verify(inventoryRecordRepository).saveInventoryRecord(inventory);
        verify(bookAvailabilityMessagingOutPort).sendMessage(any());
    }
}
