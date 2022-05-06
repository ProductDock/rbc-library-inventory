package com.productdock.library.inventory.book;

import com.productdock.library.inventory.domain.Inventory;
import com.productdock.library.inventory.domain.RentalRecord;
import com.productdock.library.inventory.exception.InventoryException;
import com.productdock.library.inventory.producer.Publisher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.productdock.library.inventory.data.provider.InventoryMother.*;
import static com.productdock.library.inventory.data.provider.InventoryRecordEntityMother.defaultBookEntityBuilder;
import static com.productdock.library.inventory.data.provider.InventoryRecordEntityMother.defaultInventoryRecordEntity;
import static com.productdock.library.inventory.data.provider.RentalRecordMessageMother.defaultRentalRecordMessage;
import static com.productdock.library.inventory.data.provider.RentalRecordMother.defaultRentalRecord;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class InventoryRecordServiceShould {

    @InjectMocks
    private InventoryRecordService inventoryRecordService;

    @Mock
    private InventoryRecordRepository inventoryRecordRepository;

    @Mock
    private Publisher publisher;

    @Mock
    private InventoryRecordMapper inventoryRecordMapper;

    @Test
    void updateBookState_whenBookExists() throws Exception {
        var bookEntity = Optional.of(mock(InventoryRecordEntity.class));
        var rentalRecord = defaultRentalRecord();
        given(inventoryRecordRepository.findById(rentalRecord.getBookId())).willReturn(bookEntity);
        var inventory = mock(Inventory.class);
        given(inventoryRecordMapper.toDomain(bookEntity.get())).willReturn(inventory);
        given(inventoryRecordMapper.toEntity(inventory)).willReturn(bookEntity.get());

        inventoryRecordService.updateBookState(rentalRecord);

        verify(inventory).updateStateWith(rentalRecord);
        verify(inventoryRecordRepository).save(bookEntity.get());
        verify(publisher).sendMessage(any());
    }

    @Test
    void throwException_whenBookDoesNotExist() throws Exception {
        var rentalRecord = defaultRentalRecord();
        given(inventoryRecordRepository.findById(rentalRecord.getBookId())).willReturn(Optional.empty());

        assertThatThrownBy(() -> inventoryRecordService.updateBookState(rentalRecord))
                .isInstanceOf(InventoryException.class);
    }
}
