package com.productdock.library.inventory.book;

import com.productdock.library.inventory.producer.Publisher;
import com.productdock.library.inventory.record.RentalRecordMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.productdock.library.inventory.data.provider.BookMother.*;
import static com.productdock.library.inventory.data.provider.RentalRecordMother.defaultRentalRecord;
import static com.productdock.library.inventory.data.provider.RentalRecordMother.defaultRentalRecordMessage;
import static org.mockito.BDDMockito.given;
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

    @Mock
    private RentalRecordMapper rentalRecordMapper;

    @BeforeEach
    final void before() {
        inventoryRecordRepository.deleteAll();
    }

    @Test
    void updateBookState_whenBookExists() throws Exception {
        var rentalRecordMessage = defaultRentalRecordMessage();
        var bookEntity = Optional.of(defaultInventoryRecordEntity());
        var inventory = defaultInventory();
        var rentalRecord = defaultRentalRecord();
        given(inventoryRecordRepository.findById(rentalRecordMessage.getBookId())).willReturn(bookEntity);
        given(rentalRecordMapper.toDomain(rentalRecordMessage)).willReturn(rentalRecord);
        given(inventoryRecordMapper.toDomain(bookEntity.get())).willReturn(inventory);
        given(inventoryRecordMapper.toEntity(inventory)).willReturn(bookEntity.get());

        inventoryRecordService.updateBookState(rentalRecordMapper.toDomain(rentalRecordMessage));

        verify(inventoryRecordRepository).save(bookEntity.get());
    }

    @Test
    void updateBookState_whenBookDoesNotExist() throws Exception {
        var rentalRecordMessage = defaultRentalRecordMessage();
        var bookEntity = Optional.of(defaultBookEntityBuilder().rentedBooks(1).build());
        var rentalRecord = defaultRentalRecord();
        given(inventoryRecordRepository.findById(rentalRecordMessage.getBookId())).willReturn(Optional.empty());
        given(rentalRecordMapper.toDomain(rentalRecordMessage)).willReturn(rentalRecord);

        inventoryRecordService.updateBookState(rentalRecordMapper.toDomain(rentalRecordMessage));

        verify(inventoryRecordRepository).save(bookEntity.get());
    }
}
